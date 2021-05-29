package entities;

import value_objects.ShopNameVO;
import value_objects.ShoppingItemVO;
import value_objects.UsernameVO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBillEntity {
    private int ID;
    private ShopNameVO shopName;
    private UsernameVO user;
    private final List<ShoppingItemVO> items;

    public ShoppingBillEntity(int ID, ShopNameVO shopName, UsernameVO user) {
        this(ID, shopName, user, new ArrayList<>());
    }

    public ShoppingBillEntity(int ID, ShopNameVO shopName, UsernameVO user, List<ShoppingItemVO> items) {
        this.ID = ID;
        this.shopName = shopName;
        this.user = user;
        this.items = items;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ShopNameVO getShopName() {
        return shopName;
    }

    public void setShopName(ShopNameVO shopName) {
        this.shopName = shopName;
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

    public void addItem(ShoppingItemVO item) {
        items.add(item);
    }
}
