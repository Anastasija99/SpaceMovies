package mk.ukim.finki.spacemovies.service.impl;

import mk.ukim.finki.spacemovies.model.Role;
import mk.ukim.finki.spacemovies.model.User;
import mk.ukim.finki.spacemovies.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.spacemovies.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.spacemovies.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.spacemovies.repository.UserRepository;
import mk.ukim.finki.spacemovies.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String firstname, String surname, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), firstname, surname, role);
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
}
