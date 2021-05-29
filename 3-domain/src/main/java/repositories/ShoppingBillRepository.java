package repositories;

import entities.ShoppingBillEntity;
import entities.ShoppingItemEntity;
import value_objects.ShopNameVO;
import value_objects.UsernameVO;
import value_objects.UuidVO;

import java.util.List;

public interface ShoppingBillRepository {
    void addShoppingBill(ShoppingBillEntity shoppingBill);

    ShoppingBillEntity addShoppingBill(int ID, ShopNameVO shopName, UsernameVO user, List<ShoppingItemEntity> items);

    ShoppingBillEntity getEntryByID(int ID);

    List<ShoppingBillEntity> getAllShoppingBillsForUser(UuidVO uuid);


}
