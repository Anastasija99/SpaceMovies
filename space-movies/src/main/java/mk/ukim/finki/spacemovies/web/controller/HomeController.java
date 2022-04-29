package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.service.GenreService;
import mk.ukim.finki.spacemovies.service.MovieService;
import mk.ukim.finki.spacemovies.service.MovieTheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final MovieService movieService;
    private final MovieTheatreService movieTheatreService;
    private final GenreService genreService;

    public HomeController(MovieService movieService, MovieTheatreService movieTheatreService, GenreService genreService) {
        this.movieService = movieService;
        this.movieTheatreService = movieTheatreService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getHomePage(Model model, @RequestParam(required = false) String city, @RequestParam(required = false) Long genreId) {

        List<Movie> movieList = this.movieService.findAll();
        List<Genre> genreList = this.genreService.listGenres();
        List<MovieTheatre> theatreList = this.movieTheatreService.findAll();
        if (city != null) {
            theatreList = this.movieTheatreService.findByCity(city);
        } else if (genreId != null) {
            movieList = this.movieService.findAllByGenre(genreId);
        }
        model.addAttribute("theatres", theatreList);
        model.addAttribute("genres", genreList);
        model.addAttribute("movies", movieList);
        model.addAttribute("sectionComponent", "homePage");
        model.addAttribute("latestMovies", movieService.getLatestMovies());
        return "masterSkeleton";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {

        return "403";
    }
}
