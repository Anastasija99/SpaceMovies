package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Actor repository.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    Optional<Actor> findActorById(Long id);

    List<Actor> findAllByFirstNameContaining(String firstname);

    List<Actor> findAllByLastNameLike(String lastname);

    List<Actor> findAllByFirstNameAndLastNameLike(String firstname, String lastname);

    List<Actor> findAllByCountryOfOriginContaining(String countryOfOrigin);
}
