package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.Actor;

import java.util.List;

public interface ActorService {

    Actor create(String firstName, String lastName);

    Actor update(String firstName, String lastName);

    void delete(Long id);

    List<Actor> listActors();

    List<Actor> searchActorsByName(String firstname);

    List<Actor> searchActorsByCountry(String country);
}
