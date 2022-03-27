package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.repository.MovieTheatreRepository;
import mk.ukim.finki.spacemovies.service.MovieTheatreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieTheatreServiceImplementation implements MovieTheatreService {

    private final MovieTheatreRepository movieTheatreRepository;

    public MovieTheatreServiceImplementation(MovieTheatreRepository movieTheatreRepository) {
        this.movieTheatreRepository = movieTheatreRepository;
    }

    @Override
    public List<MovieTheatre> findAll() {
        return this.movieTheatreRepository.findAll();
    }

    @Override
    public Optional<MovieTheatre> findById(Long id) {
        return this.movieTheatreRepository.findById(id);
    }

    @Override
    public List<MovieTheatre> findByCity(String city) {
        return this.movieTheatreRepository.findAllByCity(city);
    }

    @Override
    public void deleteById(Long id) {
        this.movieTheatreRepository.deleteById(id);
    }
}
