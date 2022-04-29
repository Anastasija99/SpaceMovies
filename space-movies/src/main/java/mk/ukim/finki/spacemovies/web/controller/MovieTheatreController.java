package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.service.MovieTheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/theatres")
public class MovieTheatreController {

    private final MovieTheatreService movieTheatreService;

    public MovieTheatreController(MovieTheatreService movieTheatreService) {
        this.movieTheatreService = movieTheatreService;
    }

    @GetMapping
    public String getMovieTheatresPage(@RequestParam(required = false) String error, @RequestParam(required = false) String city, @RequestParam(required = false) String name, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<MovieTheatre> theatreList = this.movieTheatreService.findAll();
        if (city != null) {
            theatreList = this.movieTheatreService.findByCity(city);
        }
        if (name != null) {
            theatreList = this.movieTheatreService.findByName(name);
        }

        model.addAttribute("theatres", theatreList);
        model.addAttribute("sectionComponent", "theatres");

        return "masterSkeleton";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTheatre(@PathVariable Long id) {
        this.movieTheatreService.deleteById(id);

        return "redirect:/theatres";
    }

    @GetMapping("/edit-theatre/{id}")
    public String editTheatre(@PathVariable Long id, Model model) {
        if (this.movieTheatreService.findAll() != null) {
            Optional<MovieTheatre> theatre = this.movieTheatreService.findById(id);
            model.addAttribute("theatre", theatre.get());
            model.addAttribute("sectionComponent", "add-theatre");
            return "masterSkeleton";
        }
        return "redirect:/theatres?error=TheatreNotFound";
    }

    @GetMapping("/add-theatre")
    public String addTheatre(Model model) {
        model.addAttribute("sectionComponent", "add-theatre");
        return "masterSkeleton";
    }

    @PostMapping("/add")
    public String saveTheatre(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam String address,
            @RequestParam Integer movieTheatreHalls
    ) {
        if (id != null) {
            this.movieTheatreService.update(id, name, city, address, movieTheatreHalls);
        } else {
            this.movieTheatreService.create(name, city, address, movieTheatreHalls);
        }
        return "redirect:/theatres";
    }
}
