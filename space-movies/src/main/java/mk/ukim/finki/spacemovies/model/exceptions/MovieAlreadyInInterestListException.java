package mk.ukim.finki.spacemovies.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieAlreadyInInterestListException extends RuntimeException{
    public MovieAlreadyInInterestListException() {
        super(String.format("This movie is already in your interest list!"));
    }
}
