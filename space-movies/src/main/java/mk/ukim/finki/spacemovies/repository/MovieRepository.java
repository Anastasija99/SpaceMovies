package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Movie repository.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
