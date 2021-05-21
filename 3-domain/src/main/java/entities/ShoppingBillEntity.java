package entities;

import value_objects.ShopNameVO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBillEntity {
    private int ID;
    private ShopNameVO shopName;
    private final List<ShoppingItemEntity> items;

    public ShoppingBillEntity(int ID, ShopNameVO shopName) {
        this(ID, shopName, new ArrayList<>());
    }

    public ShoppingBillEntity(int ID, ShopNameVO shopName, List<ShoppingItemEntity> items) {
        this.ID = ID;
        this.shopName = shopName;
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

    public List<ShoppingItemEntity> getItems() {
        return items;
    }

    public void addItem(ShoppingItemEntity item) {
        items.add(item);
    }
}
