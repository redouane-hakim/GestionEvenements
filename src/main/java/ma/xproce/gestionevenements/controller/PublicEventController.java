package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.Participant;
import ma.xproce.gestionevenements.dao.entities.Question;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.ParticipantService;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class PublicEventController {

    @Autowired
    EvenementService evenementService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    QuestionService questionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("events", evenementService.findAll());
        return "events";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var event = evenementService.findById(id).orElseThrow();
        model.addAttribute("event", event);
        model.addAttribute("participant", new Participant());
        model.addAttribute("question", new Question());
        model.addAttribute("questions", questionService.findByEvenement(id));
        return "event-detail";
    }

    @PostMapping("/{id}/participer")
    public String participer(@PathVariable Long id, @ModelAttribute Participant participant) {
        var event = evenementService.findById(id).orElseThrow();
        participant.setEvenement(event);
        participantService.save(participant);
        return "redirect:/events/" + id + "?okParticipation=1";
    }

    @PostMapping("/{id}/question")
    public String poserQuestion(@PathVariable Long id, @ModelAttribute Question question) {
        var event = evenementService.findById(id).orElseThrow();
        question.setEvenement(event);
        questionService.save(question);
        return "redirect:/events/" + id + "?okQuestion=1";
    }
}
