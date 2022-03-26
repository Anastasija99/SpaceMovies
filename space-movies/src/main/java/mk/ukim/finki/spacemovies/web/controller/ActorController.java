package mk.ukim.finki.spacemovies.web.controller;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public String getActorPage(Model model, String firstname, String country){
        List<Actor> actorList = this.actorService.listActors();
        if (firstname !=null) {
            actorList = this.actorService.searchActorsByName(firstname);
        }
        else if (country !=null) {
            actorList = this.actorService.searchActorsByCountry(country);
        }
        model.addAttribute("actors", actorList);
        model.addAttribute("sectionComponent", "actors");
        return "masterSkeleton";
    }
}
