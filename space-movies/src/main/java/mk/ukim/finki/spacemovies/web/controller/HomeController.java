package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("sectionComponent", "homePage");
        model.addAttribute("latestMovies", movieService.getLatestMovies());
        return "masterSkeleton";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {

        return "403";
    }
}
