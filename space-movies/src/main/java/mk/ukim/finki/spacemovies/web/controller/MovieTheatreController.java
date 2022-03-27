package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.service.MovieTheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/theatres")
public class MovieTheatreController {

    private final MovieTheatreService movieTheatreService;

    public MovieTheatreController(MovieTheatreService movieTheatreService) {
        this.movieTheatreService = movieTheatreService;
    }

    @GetMapping
    public String getMovieTheatresPage(@RequestParam(required = false) String error, String city, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<MovieTheatre> theatreList = this.movieTheatreService.findAll();
        if (city != null) {
            theatreList = this.movieTheatreService.findByCity(city);
        }

        model.addAttribute("theatres", theatreList);
        model.addAttribute("sectionComponent", "theatres");

        return "masterSkeleton";
    }
}
