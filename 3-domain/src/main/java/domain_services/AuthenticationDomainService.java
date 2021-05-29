package domain_services;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

public interface AuthenticationDomainService {
    UserAggregate login(UsernameVO username, PasswordVO password);

    UserAggregate register(UsernameVO username, PasswordVO password);
}
