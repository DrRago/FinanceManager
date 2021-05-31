package value_objects;

import java.util.Objects;

public class ShoppingItemVO {
    private final double price;
    private final ShoppingItemNameVO itemName;

    public ShoppingItemVO(double price, ShoppingItemNameVO itemName) {
        if (isValid(itemName)) {
            this.price = price / 100;
            this.itemName = itemName;
            return;
        }

        throw new IllegalArgumentException("ItemName mustn't be null");
    }

    /**
     * ShoppingItem validity check. ShoppingItem itemName mustn't be null
     *
     * @param itemName the item name
     * @return whether the ShoppingItem is valid
     */
    private boolean isValid(ShoppingItemNameVO itemName) {
        return itemName != null;
    }

    public double getPrice() {
        return price;
    }

    public ShoppingItemNameVO getItemName() {
        return itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItemVO that = (ShoppingItemVO) o;
        return price == that.price && Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, itemName);
    }
}
