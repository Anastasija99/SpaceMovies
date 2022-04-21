package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.Actor;
import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.MovieTheatre;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;
import mk.ukim.finki.spacemovies.model.exceptions.NotFoundException;
import mk.ukim.finki.spacemovies.repository.ActorRepository;
import mk.ukim.finki.spacemovies.repository.GenreRepository;
import mk.ukim.finki.spacemovies.repository.MovieRepository;
import mk.ukim.finki.spacemovies.repository.MovieTheatreRepository;
import mk.ukim.finki.spacemovies.service.MovieService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImplementation implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final MovieTheatreRepository movieTheatreRepository;

    public MovieServiceImplementation(MovieRepository movieRepository, GenreRepository genreRepository, ActorRepository actorRepository, MovieTheatreRepository movieTheatreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
        this.movieTheatreRepository = movieTheatreRepository;
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return this.movieRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public Optional<Movie> save(String title, Integer duration, LocalDate releaseDate, Float price, String description,
                                LanguageEnum language, Long genreId, List<Long> actorsIds, List<Long> theatresIds, String url) {

        Genre genre = this.genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(genreId));

        List<Actor> actors = this.actorRepository.findAllById(actorsIds);

        List<MovieTheatre> theatres = this.movieTheatreRepository.findAllById(theatresIds);

        this.movieRepository.deleteByTitle(title);

        Movie movie = new Movie(title, duration, releaseDate, price, description, language, genre, actors, theatres, url);

        return Optional.of(this.movieRepository.save(movie));
    }

    @Override
    public Optional<Movie> edit(Long id, String title, Integer duration, LocalDate releaseDate, Float price,
                                String description, LanguageEnum language, Long genreId, List<Long> actorsIds,
                                List<Long> theatresIds, String url) {

        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        movie.setTitle(title);
        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate);
        movie.setPrice(price);
        movie.setDescription(description);
        movie.setLanguage(language);
        Genre genre = this.genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(genreId));
        movie.setGenre(genre);
        List<Actor> actors = this.actorRepository.findAllById(actorsIds);
        movie.setActors(actors);
        List<MovieTheatre> theatres = this.movieTheatreRepository.findAllById(theatresIds); //moze da se razmisli da ne bide tuka tuku vo drugoto
        movie.setTheatres(theatres);

        return Optional.of(this.movieRepository.save(movie));
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findAllByGenre(Long searchGenre) {
        Genre genre = searchGenre != null ?
                this.genreRepository.findById(searchGenre).orElseThrow(() -> new NotFoundException(searchGenre))
                : null;

        if (genre != null) {
            return this.movieRepository.findAllByGenre(genre);
        } else return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> getLatestMovies() {
        return movieRepository.getLatestMovies();
    }
}
