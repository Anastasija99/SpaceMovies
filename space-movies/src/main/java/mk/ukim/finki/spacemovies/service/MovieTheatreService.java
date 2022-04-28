package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.MovieTheatre;

import java.util.List;
import java.util.Optional;

public interface MovieTheatreService {

    List<MovieTheatre> findAll();

    Optional<MovieTheatre> findById(Long id);

    List<MovieTheatre> findByCity(String city);

    List<MovieTheatre> findByName(String name);

    void deleteById(Long id);

    MovieTheatre update(Long id, String name, String city, String address, Integer movieTheatreHalls);

    MovieTheatre create(String name, String city, String address, Integer movieTheatreHalls);

}
