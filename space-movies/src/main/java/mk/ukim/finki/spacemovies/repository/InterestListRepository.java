package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.InterestList;
import mk.ukim.finki.spacemovies.model.User;
import mk.ukim.finki.spacemovies.model.enumerations.InterestListEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * InterestList repository.
 */
@Repository
public interface InterestListRepository extends JpaRepository<InterestList, Long> {

    Optional<InterestList> findByUserAndStatus(User user, InterestListEnum status);
}
