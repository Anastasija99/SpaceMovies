package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Actor repository.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
