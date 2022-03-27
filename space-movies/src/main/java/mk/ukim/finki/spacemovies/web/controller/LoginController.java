package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    //TODO: POST MAPPING FOR LOGIN after userService is done
//    @PostMapping
//    public String login(HttpServletRequest req, Model model){
//        User user = null;
//        try {
//
//        }
//        catch ()
//    }

}
