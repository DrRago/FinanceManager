package repositories;

import value_objects.ShoppingItemVO;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingBillItemRepository {
    ShoppingItemVO getItemByID(int id) throws SQLException;

    void addItem(ShoppingItemVO item, int shoppingBillID) throws SQLException;

    List<ShoppingItemVO> getAllItemsForBill(int shoppingBillID) throws SQLException;
}
