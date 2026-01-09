package ma.xproce.gestionevenements.controller;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    EvenementService evenementService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("latest5", evenementService.findLatest5());
        model.addAttribute("moreEvents", evenementService.findAll().stream().limit(6).toList());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
