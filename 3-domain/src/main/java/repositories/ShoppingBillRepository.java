package repositories;

import entities.ShoppingBillEntity;
import value_objects.DateVO;
import value_objects.ShopNameVO;
import value_objects.ShoppingItemVO;
import value_objects.UsernameVO;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingBillRepository {
    ShoppingBillEntity addShoppingBill(ShopNameVO shopName, DateVO date, UsernameVO user, List<ShoppingItemVO> items) throws SQLException;

    ShoppingBillEntity getEntryByID(int ID) throws SQLException;

    List<ShoppingBillEntity> getAllShoppingBillsForUser(UsernameVO user) throws SQLException;


}
