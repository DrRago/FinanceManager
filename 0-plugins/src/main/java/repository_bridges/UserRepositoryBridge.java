package repository_bridges;

import aggregates.UserAggregate;
import repositories.UserRepository;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class UserRepositoryBridge implements UserRepository {
    Connection connection = null;

    public UserRepositoryBridge() {
        // prepare database
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("create table if not exists user (id TEXT constraint user_pk primary key, username TEXT, password TEXT not null)");
            statement.executeUpdate("create unique index if not exists user_username_uindex on user (username)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public UserAggregate addUser(UsernameVO username, PasswordVO password) throws SQLException {
        UuidVO uuid = new UuidVO(UUID.randomUUID().toString());
        UserAggregate user = new UserAggregate(uuid, username, password, new ArrayList<>());
        addUser(user);

        return user;
    }

    @Override
    public void addUser(UserAggregate user) throws SQLException {
        PreparedStatement userAddStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
        userAddStatement.setString(1, user.getUuid().getUuid());
        userAddStatement.setString(2, user.getUsername().getUsername());
        userAddStatement.setString(3, user.getPassword().getPassword());

        userAddStatement.execute();
    }

    @Override
    public UserAggregate getUserByID(UuidVO id) throws SQLException {
        PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
        getUserStatement.setString(1, id.getUuid());

        ResultSet userRes = getUserStatement.executeQuery();
        if (!userRes.next()) {
            return null;
        }
        UsernameVO username = new UsernameVO(userRes.getString("username"));
        PasswordVO password = new PasswordVO(userRes.getString("password"));

        return new UserAggregate(id, username, password, new ArrayList<>());
    }

    @Override
    public UserAggregate getUserByUsername(UsernameVO username) throws SQLException {
        PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
        getUserStatement.setString(1, username.getUsername());

        ResultSet userRes = getUserStatement.executeQuery();
        if (!userRes.next()) {
            return null;
        }
        UuidVO uuid = new UuidVO(userRes.getString("id"));
        PasswordVO password = new PasswordVO(userRes.getString("password"));

        return new UserAggregate(uuid, username, password, new ArrayList<>());
    }

    @Override
    public UserAggregate getUserByUsernameAndPassword(UsernameVO username, PasswordVO password) throws SQLException {
        PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        getUserStatement.setString(1, username.getUsername());
        getUserStatement.setString(1, password.getPassword());

        ResultSet userRes = getUserStatement.executeQuery();
        if (!userRes.next()) {
            return null;
        }
        UuidVO uuid = new UuidVO(userRes.getString("id"));

        return new UserAggregate(uuid, username, password, new ArrayList<>());
    }
}
