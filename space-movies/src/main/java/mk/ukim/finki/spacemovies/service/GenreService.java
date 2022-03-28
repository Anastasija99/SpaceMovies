package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String name, String description);

    Genre update(String name, String description);

    Optional<Genre> findById(Long id);

    void delete(String name);

    List<Genre> listGenres();

    List<Genre> searchGenres(String searchText);
}
