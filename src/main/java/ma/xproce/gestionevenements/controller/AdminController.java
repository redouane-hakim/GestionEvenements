package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
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
        Utilisateur validateur = principal.getUtilisateur();
        var d = demandeService.accepter(id, validateur);

        // créer automatiquement l'événement (brouillon) si accepté
        // on essaie de retrouver l'organisateur via emailOrganisateur
        utilisateurService.findByEmail(d.getEmailOrganisateur())
                .ifPresent(org -> evenementService.creerDepuisDemandeAcceptee(d, org));

        return "redirect:/admin/demandes?ok=1";
    }

    @PostMapping("/demandes/{id}/refuser")
    public String refuser(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal) {
        demandeService.refuser(id, principal.getUtilisateur());
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
        questionService.repondre(qid, reponse, principal.getUtilisateur());
        return "redirect:/events/" + q.getEvenement().getEid() + "?okReponse=1";
    }
}
