package domain_services;

import aggregates.UserAggregate;

public interface AuthenticationDomainService {
    boolean login(String username, String password);

    void logout();

    boolean register(String username, String password);

    UserAggregate getUser();
}
