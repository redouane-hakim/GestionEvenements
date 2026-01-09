package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.dto.UtilisateurDto;
import ma.xproce.gestionevenements.security.UserDetailsImpl;
import ma.xproce.gestionevenements.service.DemandeService;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.QuestionService;
import ma.xproce.gestionevenements.service.UtilisateurService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final DemandeService demandeService;
    private final EvenementService evenementService;
    private final UtilisateurService utilisateurService;
    private final QuestionService questionService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("demandes", demandeService.findAll().stream().limit(8).toList());
        model.addAttribute("events", evenementService.findAll().stream().limit(8).toList());
        model.addAttribute("usersCount", utilisateurService.findAll().size());
        return "admin/dashboard";
    }

    @GetMapping("/demandes")
    public String demandes(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        return "admin/demandes";
    }

    @PostMapping("/demandes/{id}/accepter")
    public String accepter(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
        Utilisateur validateurEntity = principal.getUtilisateur();

        // Conversion en DTO
        UtilisateurDto validateur = new UtilisateurDto(
                validateurEntity.getUid(),
                validateurEntity.getNom(),
                validateurEntity.getPrenom(),
                validateurEntity.getEmail(),
                validateurEntity.getTelephone(),
                validateurEntity.getRole(),
                validateurEntity.getSignatureImageUrl(),
                validateurEntity.getDateExpirationRole()
        );

        var d = demandeService.accepter(id, validateur);

        // créer automatiquement l'événement (brouillon) si accepté
        utilisateurService.findByEmail(d.getEmailOrganisateur())
                .ifPresent(org -> evenementService.creerDepuisDemandeAcceptee(d, org));

        return "redirect:/admin/demandes?ok=1";
    }

    @PostMapping("/demandes/{id}/refuser")
    public String refuser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
        Utilisateur u = principal.getUtilisateur();
        UtilisateurDto validateur = new UtilisateurDto(
                u.getUid(), u.getNom(), u.getPrenom(), u.getEmail(),
                u.getTelephone(), u.getRole(), u.getSignatureImageUrl(), u.getDateExpirationRole()
        );
        demandeService.refuser(id, validateur);
        return "redirect:/admin/demandes?ok=1";
    }

    @GetMapping("/events")
    public String events(Model model) {
        model.addAttribute("events", evenementService.findAll());
        return "admin/events";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", utilisateurService.findAll());
        return "admin/users";
    }

    @PostMapping("/questions/{qid}/repondre")
    public String repondre(@PathVariable Long qid,
                           @RequestParam String reponse,
                           @AuthenticationPrincipal UserDetailsImpl principal) {
        var q = questionService.findById(qid).orElseThrow();

        Utilisateur u = principal.getUtilisateur();
        UtilisateurDto repondeur = new UtilisateurDto(
                u.getUid(), u.getNom(), u.getPrenom(), u.getEmail(),
                u.getTelephone(), u.getRole(), u.getSignatureImageUrl(), u.getDateExpirationRole()
        );

        questionService.repondre(qid, reponse, repondeur);
        return "redirect:/events/" + q.getEvenementEid() + "?okReponse=1";
    }
}
