package repositories;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

public interface UserRepository {
    UserAggregate addUser(UsernameVO username, PasswordVO password);

    void addUser(UserAggregate user);

    UserAggregate getUserByID(UuidVO id);

    UserAggregate getUserByUsername(UsernameVO username);

    UserAggregate getUserByUsernameAndPassword(UsernameVO username, PasswordVO password);
}
