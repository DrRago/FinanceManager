import aggregates.UserAggregate;
import authentication.Sha512Hash;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;
import repository_bridges.UserRepositoryBridge;
import services.AuthenticationService;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthenticationServiceTest {
    @Test
    public void registerLogin_newUser_createUserLoginRemoveAfterwards() throws SQLException {
        // actually tests register, login and user removal due to implementation

        UsernameVO username = new UsernameVO(Sha512Hash.hash(""));
        PasswordVO password = new PasswordVO(Sha512Hash.hash(""));

        UserRepository userRepo = new UserRepositoryBridge();
        AuthenticationService authService = new AuthenticationService(userRepo);

        UserAggregate user = authService.register(username, password);
        assertNotNull(user);

        UserAggregate loggedIn = authService.login(username, password);

        assertEquals(loggedIn, user);

        userRepo.removeUser(user.getUuid());
    }
}
