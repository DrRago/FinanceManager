package repository_bridges;

import entities.ShoppingBillEntity;
import entities.ShoppingItemEntity;
import repositories.ShoppingBillRepository;
import value_objects.ShopNameVO;
import value_objects.UuidVO;

import java.util.List;

public class ShoppingBillRepositoryBridge implements ShoppingBillRepository {
    @Override
    public void addShoppingBill(ShoppingBillEntity shoppingBill) {

    }

    @Override
    public ShoppingBillEntity addShoppingBill(int ID, ShopNameVO shopName, List<ShoppingItemEntity> items) {
        return null;
    }

    @Override
    public ShoppingBillEntity getEntryByID(int ID) {
        return null;
    }

    @Override
    public List<ShoppingBillEntity> getAllShoppingBillsForUser(UuidVO uuid) {
        return null;
    }
}
