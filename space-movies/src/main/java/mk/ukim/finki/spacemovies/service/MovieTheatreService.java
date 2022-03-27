package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.MovieTheatre;

import java.util.List;
import java.util.Optional;

public interface MovieTheatreService {

    List<MovieTheatre> findAll();

    Optional<MovieTheatre> findById(Long id);

    List<MovieTheatre> findByCity(String city);

    void deleteById(Long id);
}
