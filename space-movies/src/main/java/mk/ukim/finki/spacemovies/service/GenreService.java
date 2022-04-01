package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String name, String description);

    Genre update(Long id, String name, String description);

    Genre findById(Long id);

    void delete(String name);

    List<Genre> listGenres();

    List<Genre> searchGenres(String searchText);
}
