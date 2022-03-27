package mk.ukim.finki.spacemovies.service;

import mk.ukim.finki.spacemovies.model.InterestList;
import mk.ukim.finki.spacemovies.model.Movie;

import java.util.List;

public interface InterestListService {

    List<Movie> listAllMoviesInInterestList(Long interestID);
    InterestList getActiveInterestList(String username);
    InterestList addMovieToInterestList(String username, Long movieId);
}
