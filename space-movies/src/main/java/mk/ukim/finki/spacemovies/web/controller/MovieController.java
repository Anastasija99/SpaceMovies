package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Movie> movies = this.movieService.findAll();
        model.addAttribute("movies", movies);

        model.addAttribute("sectionComponent", "movies");

        return "masterSkeleton";
    }

    // TODO: editMovie, saveMovie, deleteMovie, addMovie
}
