package repositories;

import entities.ShoppingItemEntity;
import value_objects.ShoppingItemNameVO;

import java.util.List;

public interface ShoppingBillItemRepository {
    ShoppingItemEntity getItemByID();

    ShoppingItemEntity addItem(double price, ShoppingItemNameVO name, int shoppingBillID);

    List<ShoppingItemEntity> getAllItemsForBill(int shoppingBillID);
}
