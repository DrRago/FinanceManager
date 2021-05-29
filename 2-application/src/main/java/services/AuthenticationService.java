package services;

import aggregates.UserAggregate;
import domain_services.AuthenticationDomainService;
import repositories.UserRepository;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AuthenticationService implements AuthenticationDomainService {
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
    public UserAggregate register(UsernameVO username, PasswordVO password) {
        try {
            UuidVO uuid = new UuidVO(UUID.randomUUID().toString());
            UserAggregate user = new UserAggregate(uuid, username, password, new ArrayList<>());

            userRepository.addUser(user);

            return user;
        } catch (SQLException throwable) {
            // user already exists
            throwable.printStackTrace();
            return null;
        }
    }
}
