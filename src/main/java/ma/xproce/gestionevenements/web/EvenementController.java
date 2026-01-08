package ma.xproce.gestionevenements.web;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.dao.repositories.DemandeRepository;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DemandeRepository demandeRepository;

    /**
     * Affiche le détail d'un évènement avec la liste des questions et le formulaire de question.
     */
    @GetMapping("/evenement/{id}")
    public String showEvenement(@PathVariable("id") int id, Model model) {
        Optional<Evenement> evOpt = evenementService.findById(id);
        if (evOpt.isEmpty()) {
            return "error/404";
        }
        Evenement ev = evOpt.get();
        model.addAttribute("evenement", ev);

        // questions pour cet évènement
        model.addAttribute("questions", questionService.findByEvenement(ev));

        // formulaire de nouvelle question
        Question questionForm = new Question();
        model.addAttribute("questionForm", questionForm);

        return "evenement/detail";
    }

    /**
     * Soumet une nouvelle question pour un évènement donné.
     */
    @PostMapping("/evenement/{id}/question")
    public String submitQuestion(@PathVariable("id") int id,
                                 @ModelAttribute("questionForm") Question questionForm,
                                 RedirectAttributes ra) {
        Optional<Evenement> evOpt = evenementService.findById(id);
        if (evOpt.isEmpty()) {
            ra.addFlashAttribute("error", "Évènement introuvable.");
            return "redirect:/";
        }
        Evenement ev = evOpt.get();
        questionForm.setEvenement(ev);
        // dateQuestion est initialisée par l'entité par défaut; repondeur/reponse restent null
        questionService.save(questionForm);
        ra.addFlashAttribute("message", "Votre question a été envoyée.");
        return "redirect:/evenement/" + id;
    }

    /**
     * Formulaire pour déposer une demande (Demande).
     */
    @GetMapping("/demande/new")
    public String newDemandeForm(Model model) {
        model.addAttribute("demandeForm", new Demande());
        return "demande/form";
    }

    /**
     * Sauvegarde d'une demande.
     */
    @PostMapping("/demande")
    public String submitDemande(@ModelAttribute("demandeForm") Demande demandeForm,
                                RedirectAttributes ra) {
        // la dateDemande et status par défaut sont gérés dans l'entité Demande
        demandeRepository.save(demandeForm);
        ra.addFlashAttribute("message", "Demande soumise avec succès.");
        return "redirect:/demande/success";
    }

    @GetMapping("/demande/success")
    public String demandeSuccess() {
        return "demande/success";
    }
}