package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.model.exceptions.NotFoundException;
import mk.ukim.finki.spacemovies.repository.ActorRepository;
import mk.ukim.finki.spacemovies.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImplementation implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImplementation(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    @Override
    public Actor create(String firstName, String lastName, Integer age, String country) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Actor actor = new Actor(firstName, lastName, age, country);
        actorRepository.save(actor);
        return actor;
    }

    @Override
    public Actor update(Long id, String firstName, String lastName, Integer age, String country) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
//        Actor actor = new Actor(firstName, lastName);
//        actorRepository.save(actor);
//        return actor;
        Actor actor = this.actorRepository.findActorById(id).orElseThrow(() -> new NotFoundException(id));
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        actor.setAge(age);
        actor.setCountryOfOrigin(country);
        return actorRepository.save(actor);
    }

    @Override
    public void delete(Long id) {
        this.actorRepository.deleteById(id);
    }

    @Override
    public List<Actor> listActors() {
        return actorRepository.findAll();
    }

    @Override
    public List<Actor> searchActorsByName(String firstname) {
        return actorRepository.findAllByFirstNameContaining(firstname);
        // TODO : ako ima vreme za drugite find
    }

    @Override
    public List<Actor> searchActorsByCountry(String country) {
        return actorRepository.findAllByCountryOfOriginContaining(country);
    }

    @Override
    public Actor findActorById(Long id) {
        return actorRepository.findActorById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
