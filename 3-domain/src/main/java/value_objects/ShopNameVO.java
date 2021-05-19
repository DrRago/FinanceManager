package value_objects;

import java.util.Objects;

public final class ShopNameVO {
    private final String shopName;

    public ShopNameVO(String shopName) {
        if (isValid(shopName)) {
            this.shopName = shopName;
            return;
        }
        throw new IllegalArgumentException("The shop name mustn't be null or empty");
    }

    /**
     * Shop name validity check. A shop name is valid if it isn't null and not empty.
     *
     * @param shopName the shop name
     * @return whether it is valid
     */
    private boolean isValid(String shopName) {
        return shopName != null && !shopName.isEmpty();
    }

    public String getShopName() {
        return shopName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopNameVO that = (ShopNameVO) o;
        return Objects.equals(shopName, that.shopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName);
    }
}
