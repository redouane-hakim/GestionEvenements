package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dto.ParticipantDto;
import ma.xproce.gestionevenements.dto.QuestionDto;
import ma.xproce.gestionevenements.dto.EvenementDto;
import ma.xproce.gestionevenements.service.EvenementService;
import ma.xproce.gestionevenements.service.ParticipantService;
import ma.xproce.gestionevenements.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class PublicEventController {

    private final EvenementService evenementService;
    private final ParticipantService participantService;
    private final QuestionService questionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("events", evenementService.findAll());
        return "events";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        EvenementDto event = evenementService.findById(id).orElseThrow();
        model.addAttribute("event", event);
        model.addAttribute("participant", new ParticipantDto());
        model.addAttribute("question", new QuestionDto());
        model.addAttribute("questions", questionService.findByEvenement(id));
        return "event-detail";
    }

    @PostMapping("/{id}/participer")
    public String participer(@PathVariable Long id, @ModelAttribute ParticipantDto participant) {
        EvenementDto event = evenementService.findById(id).orElseThrow();
        participant.setEvenementId(event.getEid());
        participantService.save(participant);
        return "redirect:/events/" + id + "?okParticipation=1";
    }

    @PostMapping("/{id}/question")
    public String poserQuestion(@PathVariable Long id, @ModelAttribute QuestionDto question) {
        EvenementDto event = evenementService.findById(id).orElseThrow();
        question.setEvenementEid(event.getEid());
        question.setDateQuestion(LocalDateTime.now());
        questionService.save(question);
        return "redirect:/events/" + id + "?okQuestion=1";
    }
}
