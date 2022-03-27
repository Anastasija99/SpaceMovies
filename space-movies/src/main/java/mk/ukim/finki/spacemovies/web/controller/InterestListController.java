package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.InterestList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/interest-list")
public class InterestListController {

    @GetMapping
    public String getInterestListPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        // TODO: take interestList by username from InterestListService

        model.addAttribute("sectionComponent", "interest-list");

        return "masterSkeleton";
    }

    //TODO: POST MAPPING addMovieToInterestList
}
