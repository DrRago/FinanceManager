package repositories;

import aggregates.UserAggregate;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.sql.SQLException;

public interface UserRepository {
    UserAggregate addUser(UsernameVO username, PasswordVO password) throws SQLException;

    void addUser(UserAggregate user) throws SQLException;

    UserAggregate getUserByID(UuidVO id) throws SQLException;

    UserAggregate getUserByUsername(UsernameVO username) throws SQLException;

    UserAggregate getUserByUsernameAndPassword(UsernameVO username, PasswordVO password) throws SQLException;
}
