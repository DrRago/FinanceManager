package domain_services;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import javax.naming.AuthenticationException;

public interface AuthenticationDomainService {
    boolean login(UsernameVO username, PasswordVO password);

    void logout() throws AuthenticationException;

    boolean register(UsernameVO username, PasswordVO password);

    UserAggregate getUser();
}
