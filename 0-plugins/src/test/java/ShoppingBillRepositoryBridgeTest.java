import entities.ShoppingBillEntity;
import org.junit.jupiter.api.Test;
import repositories.ShoppingBillRepository;
import repository_bridges.ShoppingBillRepositoryBridge;
import value_objects.DateVO;
import value_objects.ShopNameVO;
import value_objects.UsernameVO;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ShoppingBillRepositoryBridgeTest {
    @Test
    public void add_Remove_shoppingBill_existsInDatabase() throws SQLException {
        ShoppingBillRepository repository = new ShoppingBillRepositoryBridge();

        ShopNameVO shopNameVO = new ShopNameVO("realShop");
        DateVO dateVO = new DateVO("2019-05-05");
        UsernameVO usernameVO = new UsernameVO("username");

        ShoppingBillEntity entity = repository.addShoppingBill(shopNameVO, dateVO, usernameVO, new ArrayList<>());
        ShoppingBillEntity queried = repository.getEntryByID(entity.getID());

        repository.removeEntryByID(queried.getID());

        assertEquals(queried.getID(), entity.getID());
        queried = repository.getEntryByID(entity.getID());

        assertNull(queried);
    }
}
