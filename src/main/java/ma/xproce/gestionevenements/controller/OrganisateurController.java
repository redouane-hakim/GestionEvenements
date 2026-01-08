package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.security.UserDetailsImpl;
import ma.xproce.gestionevenements.service.DemandeService;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.ParticipantService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/organisateur")
public class OrganisateurController {

    private final DemandeService demandeService;
    private final EvenementService evenementService;
    private final ParticipantService participantService;

    @GetMapping
    public String dashboard(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
        Utilisateur u = principal.getUtilisateur();
        model.addAttribute("mesDemandes", demandeService.findByOrganisateurEmail(u.getEmail()).stream().limit(8).toList());
        model.addAttribute("mesEvents", evenementService.findByOrganisateur(u.getUid()).stream().limit(8).toList());
        return "org/dashboard";
    }

    @GetMapping("/demande")
    public String demandeForm(Model model) {
        model.addAttribute("demande", new Demande());
        return "org/demande-form";
    }

    @PostMapping("/demande")
    public String submitDemande(@ModelAttribute Demande demande, @AuthenticationPrincipal UserDetailsImpl principal) {
        Utilisateur u = principal.getUtilisateur();
        // remplir infos organisateur depuis le compte connecté (traçabilité)
        demande.setNomOrganisateur(u.getNom());
        demande.setPrenomOrganisateur(u.getPrenom());
        demande.setEmailOrganisateur(u.getEmail());
        demande.setTelephoneOrganisateur(u.getTelephone());
        demande.setSignatureOrganisateurImageUrl(u.getSignatureImageUrl());
        demandeService.creer(demande);
        return "redirect:/organisateur/demandes?ok=1";
    }

    @GetMapping("/demandes")
    public String mesDemandes(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
        Utilisateur u = principal.getUtilisateur();
        model.addAttribute("demandes", demandeService.findByOrganisateurEmail(u.getEmail()));
        return "org/demandes";
    }

    @GetMapping("/events")
    public String mesEvents(@AuthenticationPrincipal UserDetailsImpl principal, Model model) {
        Utilisateur u = principal.getUtilisateur();
        model.addAttribute("events", evenementService.findByOrganisateur(u.getUid()));
        return "org/events";
    }

    @GetMapping("/events/{id}/edit")
    public String editEvent(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
        var e = evenementService.findById(id).orElseThrow();
        // simple sécurité côté serveur
        if (e.getOrganisateur() == null || e.getOrganisateur().getUid() != principal.getUtilisateur().getUid()) {
            return "redirect:/organisateur/events?forbidden=1";
        }
        model.addAttribute("event", e);
        return "org/event-edit";
    }

    @PostMapping("/events/{id}/edit")
    public String saveEvent(@PathVariable Long id, @ModelAttribute Evenement form,
                            @AuthenticationPrincipal UserDetailsImpl principal) {
        var e = evenementService.findById(id).orElseThrow();
        if (e.getOrganisateur() == null || e.getOrganisateur().getUid() != principal.getUtilisateur().getUid()) {
            return "redirect:/organisateur/events?forbidden=1";
        }
        e.setTitre(form.getTitre());
        e.setDescription(form.getDescription());
        e.setLieu(form.getLieu());
        e.setDateDebut(form.getDateDebut());
        e.setDateFin(form.getDateFin());
        e.setCategorie(form.getCategorie());
        e.setAfficheUrl(form.getAfficheUrl());
        evenementService.save(e);
        return "redirect:/organisateur/events?ok=1";
    }

    @GetMapping("/events/{id}/participants")
    public String participants(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl principal, Model model) {
        var e = evenementService.findById(id).orElseThrow();
        if (e.getOrganisateur() == null || e.getOrganisateur().getUid() != principal.getUtilisateur().getUid()) {
            return "redirect:/organisateur/events?forbidden=1";
        }
        model.addAttribute("event", e);
        model.addAttribute("participants", participantService.findByEvenement(id));
        return "org/participants";
    }
}
