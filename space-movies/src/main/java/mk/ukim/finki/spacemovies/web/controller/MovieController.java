package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;
import mk.ukim.finki.spacemovies.service.ActorService;
import mk.ukim.finki.spacemovies.service.GenreService;
import mk.ukim.finki.spacemovies.service.MovieService;
import mk.ukim.finki.spacemovies.service.MovieTheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    private final ActorService actorService;

    private final GenreService genreService;

    private final MovieTheatreService theatreService;

    public MovieController(MovieService movieService, ActorService actorService, GenreService genreService, MovieTheatreService theatreService) {
        this.movieService = movieService;
        this.actorService = actorService;
        this.genreService = genreService;
        this.theatreService = theatreService;
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

    @DeleteMapping("delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-movie/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Actor> actors = this.actorService.listActors();
            List<Genre> genres = this.genreService.listGenres();
            List<MovieTheatre> theatres = this.theatreService.findAll();
            List<LanguageEnum> languages = Arrays.stream(LanguageEnum.values()).collect(Collectors.toList());
            model.addAttribute("movies", movie);
            model.addAttribute("actors", actors);
            model.addAttribute("genres", genres);
            model.addAttribute("theatres", theatres);
            model.addAttribute("languages", languages);
            model.addAttribute("sectionComponent", "add-movie");
            return "masterSkeleton";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/add-movie")
    public String addMovie(Model model) {
        List<Actor> actors = this.actorService.listActors();
        List<Genre> genres = this.genreService.listGenres();
        List<MovieTheatre> theatres = this.theatreService.findAll();
        List<LanguageEnum> languages = Arrays.stream(LanguageEnum.values()).collect(Collectors.toList());
        model.addAttribute("actors", actors);
        model.addAttribute("genres", genres);
        model.addAttribute("theatres", theatres);
        model.addAttribute("languages", languages);
        model.addAttribute("sectionComponent", "add-movie");
        return "masterSkeleton";
    }

    @PostMapping("/add")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam Integer duration,
            @RequestParam Float price,
            @RequestParam String description,
            @RequestParam Long genre,
            @RequestParam String releaseDate,
            @RequestParam List<Long> actors,
            @RequestParam List<Long> theatres,
            @RequestParam LanguageEnum language,
            @RequestParam String url
    ) {
        if (id != null) {
            this.movieService.edit(id, title, duration, LocalDate.parse(releaseDate), price, description, language, genre, actors, theatres, url);
        } else {
            this.movieService.save(title, duration, LocalDate.parse(releaseDate), price, description, language, genre, actors, theatres, url);
        }
        return "redirect:/movies";
    }
}
