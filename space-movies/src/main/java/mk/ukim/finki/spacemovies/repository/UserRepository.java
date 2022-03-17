package mk.ukim.finki.spacemovies.repository;

import mk.ukim.finki.spacemovies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}