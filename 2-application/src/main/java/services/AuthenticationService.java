package services;

import aggregates.UserAggregate;
import domain_services.AuthenticationDomainService;
import repositories.UserRepository;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import javax.naming.AuthenticationException;

public class AuthenticationService implements AuthenticationDomainService {
    UserAggregate currentUser = null;
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(UsernameVO username, PasswordVO password) {
        UserAggregate user = userRepository.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        }
        currentUser = user;
        return true;
    }

    @Override
    public void logout() throws AuthenticationException {
        if (currentUser == null) {
            throw new AuthenticationException("No user logged in");
        }
        currentUser = null;
    }

    @Override
    public boolean register(UsernameVO username, PasswordVO password) {
        UserAggregate user = userRepository.getUserByUsername(username);
        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        currentUser = userRepository.addUser(username, password);
        return true;
    }

    @Override
    public UserAggregate getUser() {
        return currentUser;
    }
}
