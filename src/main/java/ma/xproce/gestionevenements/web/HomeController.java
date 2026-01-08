package ma.xproce.gestionevenements.web;

import ma.xproce.gestionevenements.dao.entities.Evenement;
import ma.xproce.gestionevenements.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EvenementService evenementService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<Evenement> evenements = evenementService.findAll();
        model.addAttribute("evenements", evenements);
        return "home";
    }
}