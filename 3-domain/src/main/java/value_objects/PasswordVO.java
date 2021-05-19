package value_objects;

import java.util.Objects;

public final class PasswordVO {
    private final String password;

    public PasswordVO(String password) {
        if (isValid(password)) {
            this.password = password;
            return;
        }

        throw new IllegalArgumentException("A password has to be at least eight characters long and contain two digits and letters");
    }

    /**
     * Password validity check. A password has to be at least eight characters long and contain two digits and letters
     *
     * @param password the password to check
     * @return whether the password is valid
     */
    private boolean isValid(String password) {
        if (password.length() < 8) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isLetter(ch)) numCount++;
            if (Character.isDigit(ch)) charCount++;
            if (!Character.isAlphabetic(ch)) return false; // TODO validate that it's working
        }

        return charCount >= 2 && numCount >= 2;
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
