package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Movie repository.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    Optional<Movie> deleteByTitle(String title);

    List<Movie> findAllByLanguage(LanguageEnum language);

    List<Movie> findAllByGenre(Genre genre);
}
