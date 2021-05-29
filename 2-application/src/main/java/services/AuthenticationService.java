package services;

import aggregates.UserAggregate;
import domain_services.AuthenticationDomainService;
import repositories.UserRepository;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import java.sql.SQLException;

public class AuthenticationService implements AuthenticationDomainService {
    UserAggregate currentUser = null;
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAggregate login(UsernameVO username, PasswordVO password) {
        UserAggregate user;
        try {
            user = userRepository.getUserByUsernameAndPassword(username, password);
        } catch (SQLException throwable) {
            return null;
        }
        return user;
    }

    @Override
    public boolean register(UsernameVO username, PasswordVO password) {
        try {
            UserAggregate user = userRepository.getUserByUsername(username);
            if (user != null) {
                throw new IllegalArgumentException("Username already exists");
            }
            currentUser = userRepository.addUser(username, password);
        } catch (SQLException throwable) {
            return false;
        }
        return true;
    }

    @Override
    public UserAggregate getUser() {
        return currentUser;
    }
}
