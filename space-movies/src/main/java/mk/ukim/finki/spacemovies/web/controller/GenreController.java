package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String getCategoryPage(Model model){
        model.addAttribute("genres", this.genreService.listGenres());
        model.addAttribute("sectionComponent", "genres");
        return "masterSkeleton";
    }
}
