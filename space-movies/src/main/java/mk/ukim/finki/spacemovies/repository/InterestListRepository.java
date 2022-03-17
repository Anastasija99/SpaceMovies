package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.InterestList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * InterestList repository.
 */
@Repository
public interface InterestListRepository extends JpaRepository<InterestList, Long> {
}
