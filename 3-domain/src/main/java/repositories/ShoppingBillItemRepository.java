package repositories;

import value_objects.ShoppingItemVO;
import value_objects.ShoppingItemNameVO;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingBillItemRepository {
    ShoppingItemVO getItemByID(int id) throws SQLException;

    ShoppingItemVO addItem(ShoppingItemVO item, int shoppingBillID) throws SQLException;

    ShoppingItemVO addItem(int price, ShoppingItemNameVO name, int shoppingBillID) throws SQLException;

    List<ShoppingItemVO> getAllItemsForBill(int shoppingBillID) throws SQLException;
}
