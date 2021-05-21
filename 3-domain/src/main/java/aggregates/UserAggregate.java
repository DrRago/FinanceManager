package aggregates;

import entities.ShoppingBillEntity;
import value_objects.PasswordVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.util.List;
import java.util.Objects;

public class UserAggregate {

    private final UuidVO uuid;
    private final UsernameVO username;
    private final PasswordVO password;
    private final List<ShoppingBillEntity> shoppingBills;

    public UserAggregate(UuidVO uuid, UsernameVO username, PasswordVO password, List<ShoppingBillEntity> shoppingBills) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.shoppingBills = shoppingBills;
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

    public List<ShoppingBillEntity> getShoppingBills() {
        return shoppingBills;
    }
}
