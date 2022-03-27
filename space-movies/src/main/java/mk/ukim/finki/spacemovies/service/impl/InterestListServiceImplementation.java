package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.InterestList;
import mk.ukim.finki.spacemovies.model.Movie;
import mk.ukim.finki.spacemovies.model.User;
import mk.ukim.finki.spacemovies.model.enumerations.InterestListEnum;
import mk.ukim.finki.spacemovies.model.exceptions.MovieAlreadyInInterestListException;
import mk.ukim.finki.spacemovies.model.exceptions.NotFoundException;
import mk.ukim.finki.spacemovies.model.exceptions.UsernameNotFoundException;
import mk.ukim.finki.spacemovies.repository.InterestListRepository;
import mk.ukim.finki.spacemovies.repository.MovieRepository;
import mk.ukim.finki.spacemovies.repository.UserRepository;
import mk.ukim.finki.spacemovies.service.InterestListService;
import mk.ukim.finki.spacemovies.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestListServiceImplementation implements InterestListService {

    private final InterestListRepository interestListRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;

    public InterestListServiceImplementation(InterestListRepository interestListRepository,
                                             UserRepository userRepository,
                                             MovieService movieService) {
        this.interestListRepository = interestListRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    @Override
    public List<Movie> listAllMoviesInInterestList(Long interestID) {
        if (!this.interestListRepository.findById(interestID).isPresent()) {
            throw new NotFoundException(interestID);
        }

        return this.interestListRepository.findById(interestID).get().getMovies();
    }

    @Override
    public InterestList getActiveInterestList(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return this.interestListRepository.findByUserAndStatus(user, InterestListEnum.CREATED)
                .orElseGet(() -> {
                    InterestList interestList = new InterestList(user);
                    return this.interestListRepository.save(interestList);
                });
    }

    @Override
    public InterestList addMovieToInterestList(String username, Long movieId) {
        InterestList interestList = this.getActiveInterestList(username);
        Movie movie = movieService.findById(movieId).orElseThrow(() -> new NotFoundException(movieId));

        if (interestList.getMovies().stream().filter(m -> m.getId().equals(movieId)).count() > 0) {
            throw new MovieAlreadyInInterestListException();
        }

        interestList.getMovies().add(movie);

        return this.interestListRepository.save(interestList);
    }
}
