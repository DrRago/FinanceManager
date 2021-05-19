package domain_services;

import aggregates.UserAggregate;

public interface AuthenticationService {
    boolean login(String username, String password);

    void logout();

    boolean register(String username, String password);

    UserAggregate getUser();
}
