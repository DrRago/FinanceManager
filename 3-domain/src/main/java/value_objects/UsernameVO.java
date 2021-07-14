package value_objects;

import java.io.Serializable;
import java.util.Objects;

public final class UsernameVO implements Serializable {
    private final String username;

    public UsernameVO(String username) {
        if (isValid(username)) {
            this.username = username;
            return;
        }

        throw new IllegalArgumentException("Username mustn't be null or less than three characters");
    }


    /**
     * Username validity check. Username mustn't be null or less than three characters
     *
     * @param username the username
     * @return whether the username is valid
     */
    private boolean isValid(String username) {
        return !(username == null || username.length() < 3);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsernameVO that = (UsernameVO) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
