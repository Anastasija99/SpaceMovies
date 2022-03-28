package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.repository.GenreRepository;
import mk.ukim.finki.spacemovies.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImplementation implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImplementation(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre create(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre genre = new Genre(name, description);
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public Genre update(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre genre = new Genre(name, description);
//      TODO:  Genre genre = genreRepository.findByName()
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return this.genreRepository.findById(id);
    }

    @Override
    public void delete(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        genreRepository.deleteByName(name);
    }

    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> searchGenres(String searchText) {
        return genreRepository.findAllByNameLike(searchText);
    }
}
