package value_objects;

import java.util.Objects;

public final class PasswordVO {
    private final String password;

    public PasswordVO(String password) {
        if (isValid(password)) {
            this.password = password;
            return;
        }

        throw new IllegalArgumentException("A password must be a SHA512 hash");
    }

    /**
     * Password validity check. Checks for the SHA512 hash to be 128 characters long
     *
     * @param password the password to check
     * @return whether the password is valid
     */
    private boolean isValid(String password) {
        return password.matches("^\\w{128}$");
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordVO that = (PasswordVO) o;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
