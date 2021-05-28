package domain_services;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

public interface AuthenticationDomainService {
    boolean login(UsernameVO username, PasswordVO password) throws SQLException;

    void logout() throws AuthenticationException;

    boolean register(UsernameVO username, PasswordVO password) throws SQLException;

    UserAggregate getUser();
}
