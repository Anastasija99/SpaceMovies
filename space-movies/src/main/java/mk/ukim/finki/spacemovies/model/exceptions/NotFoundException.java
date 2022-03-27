package mk.ukim.finki.spacemovies.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super(String.format("Object with id %d was not found", id));
    }
}

//TODO: DA SE NAPRAVAT POSEBNI EXCEPTIONS ZA SITE OPCII
