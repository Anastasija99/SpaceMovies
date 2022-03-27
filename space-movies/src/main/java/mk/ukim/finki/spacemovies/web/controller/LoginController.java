package mk.ukim.finki.spacemovies.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("sectionComponent", "login");

        return "masterSkeleton";
    }

    //TODO: POST MAPPING FOR LOGIN after userService is done

}
