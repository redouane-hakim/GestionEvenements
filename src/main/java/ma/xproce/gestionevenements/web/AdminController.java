package ma.xproce.gestionevenements.web;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Utilisateur;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.ParticipantService;
import ma.xproce.gestionevenements.service.RessourceService;
import ma.xproce.gestionevenements.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private RessourceService ressourceService;

    @GetMapping("/admin")
    public String adminIndex(Model model) {
        long eventsCount = evenementService.findAll().size();
        long usersCount = utilisateurService.findAll().size();
        long ressourcesCount = ressourceService.findAll().size();

        model.addAttribute("eventsCount", eventsCount);
        model.addAttribute("usersCount", usersCount);
        model.addAttribute("ressourcesCount", ressourcesCount);
        return "admin/index";
    }

    @GetMapping("/admin/evenements")
    public String adminEvenements(Model model) {
        List<Evenement> evenements = evenementService.findAll();
        model.addAttribute("evenements", evenements);
        return "admin/events";
    }

    @GetMapping("/admin/utilisateurs")
    public String adminUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "admin/users";
    }
}