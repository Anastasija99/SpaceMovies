package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.InterestList;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.service.InterestListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/interest-list")
public class InterestListController {

    private final InterestListService interestListService;

    public InterestListController(InterestListService interestListService) {
        this.interestListService = interestListService;
    }

    @GetMapping
    public String getInterestListPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        InterestList interestList  = this.interestListService.getActiveInterestList(username);
        List<Movie> movieList = this.interestListService.listAllMoviesInInterestList(interestList.getId());

        model.addAttribute("movies", movieList);
        model.addAttribute("sectionComponent", "interest-list");

        return "masterSkeleton";
    }

    @PostMapping("/add-movie/{id}")
    public String addMovieToInterestList(@PathVariable Long id, HttpServletRequest req){

        try {
            String username = req.getRemoteUser();
            InterestList interestList = this.interestListService.addMovieToInterestList(username, id);

            return "redirect:/interest-list";
        } catch (RuntimeException exception) {
            return "redirect:/interest-list?error=" + exception.getMessage();
        }
    }
}
