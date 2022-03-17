package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Genre repository.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
