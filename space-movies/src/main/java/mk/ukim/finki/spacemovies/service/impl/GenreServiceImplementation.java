package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.Genre;
import mk.ukim.finki.spacemovies.model.exceptions.NotFoundException;
import mk.ukim.finki.spacemovies.repository.GenreRepository;
import mk.ukim.finki.spacemovies.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public Genre update(Long id, String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre genre = this.genreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
//      TODO:  Genre genre = genreRepository.findByName()
        genre.setName(name);
        genre.setDescription(description);
        return genreRepository.save(genre);
    }

    @Override
    public Genre findById(Long id) {
        return this.genreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
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
