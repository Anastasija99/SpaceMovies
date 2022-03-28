package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;
import mk.ukim.finki.spacemovies.service.ActorService;
import mk.ukim.finki.spacemovies.service.GenreService;
import mk.ukim.finki.spacemovies.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    private final ActorService actorService;

    private final GenreService genreService;

    public MovieController(MovieService movieService, ActorService actorService, GenreService genreService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.genreService = genreService;
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
    @DeleteMapping("delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("edit-movie/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Actor> actors = this.actorService.listActors();
            List<Genre> genres = this.genreService.listGenres();
            model.addAttribute("movies", movie);
            model.addAttribute("actors", actors);
            model.addAttribute("genres", genres);
            model.addAttribute("masterSkeleton", "add-movies");
            return "redirect:/movies";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-movie")
    public String addMovie(Model model) {
        List<Actor> actors = this.actorService.listActors();
        List<Genre> genres = this.genreService.listGenres();
        model.addAttribute("actors", actors);
        model.addAttribute("genres", genres);
        return "masterSkeleton";
    }

    @GetMapping("/add")
    public String saveMovie(
            @PathVariable(required = false) Long id,
            @PathVariable String title,
            @PathVariable Integer duration,
            @PathVariable Float price,
            @PathVariable String description,
            @PathVariable Long genre,
            @PathVariable LocalDate releaseDate,
            @PathVariable List<Long> actors,
            @PathVariable List<Long> theatres,
            @PathVariable LanguageEnum language
    ) {
//        Genre genre = this.genreService.findById(id);
        if (id != null) {
            this.movieService.edit(id, title, duration, releaseDate, price, description, language, genre, theatres, actors);
        } else {
            this.movieService.save(title, duration, releaseDate, price, description, language, genre, theatres, actors);
        }
        return "redirect:/movies";
    }
}
