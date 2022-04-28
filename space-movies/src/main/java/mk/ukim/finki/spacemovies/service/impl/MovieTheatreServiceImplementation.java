package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.model.exceptions.NotFoundException;
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
    public MovieTheatre create(String name, String city, String address, Integer movieTheatreHalls) {
        if (name == null || name.isEmpty() || city == null || city.isEmpty() || address == null || address.isEmpty()) {
            throw new IllegalArgumentException();
        }
        MovieTheatre theatre = new MovieTheatre(name, city, address, movieTheatreHalls);
        movieTheatreRepository.save(theatre);
        return theatre;
    }

    @Override
    public MovieTheatre update(Long id, String name, String city, String address, Integer movieTheatreHalls) {
        if (name == null || name.isEmpty() || city == null || city.isEmpty() || address == null || address.isEmpty()) {
            throw new IllegalArgumentException();
        }
        MovieTheatre theatre = this.movieTheatreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        theatre.setName(name);
        theatre.setCity(city);
        theatre.setAddress(address);
        theatre.setMovieTheatreHalls(movieTheatreHalls);
        return movieTheatreRepository.save(theatre);
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
        return this.movieTheatreRepository.findAllByCityContaining(city);
    }

    @Override
    public List<MovieTheatre> findByName(String name) {
        return this.movieTheatreRepository.findAllByNameContaining(name);
    }


    @Override
    public void deleteById(Long id) {
        this.movieTheatreRepository.deleteById(id);
    }
}
