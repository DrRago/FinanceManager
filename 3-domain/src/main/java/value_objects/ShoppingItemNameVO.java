package value_objects;

import java.util.Objects;

public final class ShoppingItemNameVO {
    private final String shoppingItemName;

    public ShoppingItemNameVO(String shoppingItemName) {
        if (isValid(shoppingItemName)) {
            this.shoppingItemName = shoppingItemName;
            return;
        }
        throw new IllegalArgumentException("Shopping item name mustn't be null or empty");
    }

    /**
     * Shopping item validity check. A shopping item is valid if it isn't null or empty.
     *
     * @param shoppingItemName the shopping item name
     * @return whether it is valid
     */
    private boolean isValid(String shoppingItemName) {
        return shoppingItemName != null && !shoppingItemName.isEmpty();
    }

    public String getShoppingItemName() {
        return shoppingItemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingItemNameVO that = (ShoppingItemNameVO) o;
        return Objects.equals(shoppingItemName, that.shoppingItemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingItemName);
    }
}
