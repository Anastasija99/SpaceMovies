package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public String getActorPage(Model model, String firstname, String country) {
        List<Actor> actorList = this.actorService.listActors();
        if (firstname != null) {
            actorList = this.actorService.searchActorsByName(firstname);
        } else if (country != null) {
            actorList = this.actorService.searchActorsByCountry(country);
        }
        model.addAttribute("actors", actorList);
        model.addAttribute("sectionComponent", "actors");

        return "masterSkeleton";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id) {
        this.actorService.delete(id);

        return "redirect:/actors";
    }

    @GetMapping("/edit-actor/{id}")
    public String editActor(@PathVariable Long id, Model model) {
        if (this.actorService.listActors() != null) {
            Actor actor = this.actorService.findActorById(id);
            model.addAttribute("actor", actor);
            model.addAttribute("sectionComponent", "add-actor");
            return "masterSkeleton";
        }
        return "redirect:/actors?error=ActorNotFound";
    }

    @GetMapping("/add-actor")
    public String addActor(Model model) {
        model.addAttribute("sectionComponent", "add-actor");
        return "masterSkeleton";
    }

    @PostMapping("/add")
    public String saveActor(
            @RequestParam(required = false) Long id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String country,
            @RequestParam Integer age
    ) {
        if (id != null) {
            this.actorService.update(id, firstName, lastName, age, country);
        } else {
            this.actorService.create(firstName, lastName, age, country);
        }
        return "redirect:/actors";
    }
}
