package repository_bridges;

import aggregates.UserAggregate;
import repositories.UserRepository;
import services.DatabaseConnectionProviderService;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.sql.*;

public class UserRepositoryBridge implements UserRepository {
    final DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

    public UserRepositoryBridge() {
        dbProvider.initializeUserTable();
    }

    @Override
    public void removeUser(UuidVO id) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement userRemoveStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
        userRemoveStatement.setString(1, id.getUuid());

        userRemoveStatement.execute();
        connection.close();
    }

    @Override
    public void addUser(UserAggregate user) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement userAddStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
        userAddStatement.setString(1, user.getUuid().getUuid());
        userAddStatement.setString(2, user.getUsername().getUsername());
        userAddStatement.setString(3, user.getPassword().getPassword());

        userAddStatement.execute();
        connection.close();
    }

    @Override
    public UserAggregate getUserByUsernameAndPassword(UsernameVO username, PasswordVO password) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        getUserStatement.setString(1, username.getUsername());
        getUserStatement.setString(2, password.getPassword());

        ResultSet userRes = getUserStatement.executeQuery();
        if (!userRes.next()) {
            connection.close();
            return null;
        }
        UuidVO uuid = new UuidVO(userRes.getString("id"));

        connection.close();
        return new UserAggregate(uuid, username, password);
    }
}
