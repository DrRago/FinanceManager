package enteties;

import value_objects.ShoppingItemNameVO;

public class ShoppingItemEntity {
    private int ID;
    private int price;
    private ShoppingItemNameVO itemName;

    public ShoppingItemEntity(int ID, int price, ShoppingItemNameVO itemName) {
        this.ID = ID;
        this.price = price;
        this.itemName = itemName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ShoppingItemNameVO getItemName() {
        return itemName;
    }

    public void setItemName(ShoppingItemNameVO itemName) {
        this.itemName = itemName;
    }
}
