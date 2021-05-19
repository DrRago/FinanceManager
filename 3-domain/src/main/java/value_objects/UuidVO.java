package value_objects;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UuidVO {
    private final String uuid;

    public UuidVO(String uuid) {
        if (isValid(uuid)) {
            this.uuid = uuid;
            return;
        }

        throw new IllegalArgumentException("Not a valid uuid. Please follow ISO/IEC 11578:1996");
    }

    /**
     * UUID validity check. UUID is valid if it follows the ISO/IEC 11578:1996 standard for UUIDs in form 8-4-4-4-12
     *
     * @param uuid the uuid
     * @return whether it is valid
     */
    private boolean isValid(String uuid) {
        // regex for uuid pattern 8-4-4-4-12
        final String regex = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";
        // verify match on given uuid
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(uuid);
        return matcher.find();
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UuidVO uuidVO = (UuidVO) o;
        return Objects.equals(uuid, uuidVO.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
