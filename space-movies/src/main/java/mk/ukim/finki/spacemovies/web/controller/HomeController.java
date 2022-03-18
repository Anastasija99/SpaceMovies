package mk.ukim.finki.spacemovies.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("sectionComponent", ""); //att value e homePage.html
        return "masterSkeleton";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(Model model){
        model.addAttribute("sectionComponent", ""); //att value e 404.html
        return "masterSkeleton";
    }
}
