package entities;

import value_objects.DateVO;
import value_objects.ShopNameVO;
import value_objects.ShoppingItemVO;
import value_objects.UsernameVO;

import java.util.List;

public class ShoppingBillEntity {
    private final int ID;
    private final ShopNameVO shopName;
    private DateVO date;
    private UsernameVO user;
    private final List<ShoppingItemVO> items;

    public ShoppingBillEntity(int ID, ShopNameVO shopName, DateVO date, UsernameVO user, List<ShoppingItemVO> items) {
        this.ID = ID;
        this.shopName = shopName;
        this.date = date;
        this.user = user;
        this.items = items;
    }

    public int getID() {
        return ID;
    }

    public ShopNameVO getShopName() {
        return shopName;
    }

    public DateVO getDate() {
        return date;
    }

    public void setDate(DateVO date) {
        this.date = date;
    }

    public UsernameVO getUser() {
        return user;
    }

    public void setUser(UsernameVO user) {
        this.user = user;
    }

    public List<ShoppingItemVO> getItems() {
        return items;
    }

}
