package repositories;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.sql.SQLException;

public interface UserRepository {

    void removeUser(UuidVO id) throws SQLException;

    void addUser(UserAggregate user) throws SQLException;

    UserAggregate getUserByUsernameAndPassword(UsernameVO username, PasswordVO password) throws SQLException;
}
