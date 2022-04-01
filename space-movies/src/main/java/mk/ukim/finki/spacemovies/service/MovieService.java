package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Optional<Movie> findByTitle(String title);

    Optional<Movie> save(String title, Integer duration, LocalDate releaseDate, Float price, String description, LanguageEnum language, Long genre, List<Long> actors, List<Long> theatres);

    Optional<Movie> edit(Long id, String title, Integer duration, LocalDate releaseDate, Float price, String description, LanguageEnum language, Long genre, List<Long> actors, List<Long> theatres);

    void deleteById(Long id);

    List<Movie> getLatestMovies();

//    TODO: ako ima vreme na kraj
//    List<Movie> findAllByLanguage(String searchLanguage);
    List<Movie> findAllByGenre(Long searchGenre);

}
