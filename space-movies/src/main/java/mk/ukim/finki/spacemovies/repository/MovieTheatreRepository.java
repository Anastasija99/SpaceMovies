package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.MovieTheatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MovieTheatre repository.
 */
@Repository
public interface MovieTheatreRepository extends JpaRepository<MovieTheatre, Long> {

    List<MovieTheatre> findAllByCity(String city);
}
