package aggregates;

import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.util.Objects;

public class UserAggregate {

    private final UuidVO uuid;
    private final UsernameVO username;
    private final PasswordVO password;

    public UserAggregate(UuidVO uuid, UsernameVO username, PasswordVO password) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAggregate that = (UserAggregate) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public UsernameVO getUsername() {
        return username;
    }

    public PasswordVO getPassword() {
        return password;
    }
}
