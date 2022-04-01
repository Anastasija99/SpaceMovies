package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String getGenrePage(Model model) {
        model.addAttribute("genres", this.genreService.listGenres());
        model.addAttribute("sectionComponent", "genres");

        return "masterSkeleton";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteGenre(@PathVariable String name) {
        this.genreService.delete(name);

        return "redirect:/genres";
    }

    @GetMapping("/edit-genre/{id}")
    public String editGenre(@PathVariable Long id, Model model) {
        if (this.genreService.listGenres() != null) {
            Genre genre = this.genreService.findById(id);
            model.addAttribute("genre", genre);
            model.addAttribute("sectionComponent", "add-genre");
            return "masterSkeleton";
        }
        return "redirect:/genres?error=GenreNotFound";
    }

    @GetMapping("/add-genre")
    public String addGenre(Model model) {
        model.addAttribute("sectionComponent", "add-genre");
        return "masterSkeleton";
    }

    @PostMapping("/add")
    public String saveGenre(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description
    ) {
        if (id != null) {
            this.genreService.update(id, name, description);
        } else {
            this.genreService.create(name, description);
        }
        return "redirect:/genres";
    }
}
