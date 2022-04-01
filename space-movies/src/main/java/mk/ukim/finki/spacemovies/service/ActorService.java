package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Actor create(String firstName, String lastName, Integer age, String country);

    Actor update(Long id, String firstName, String lastName, Integer age, String country);

    void delete(Long id);

    List<Actor> listActors();

    List<Actor> searchActorsByName(String firstname);

    List<Actor> searchActorsByCountry(String country);

    Actor findActorById(Long id);
}
