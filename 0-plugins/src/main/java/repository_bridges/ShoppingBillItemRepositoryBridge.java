package repository_bridges;

import repositories.ShoppingBillItemRepository;
import services.DatabaseConnectionProviderService;
import value_objects.ShoppingItemNameVO;
import value_objects.ShoppingItemVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBillItemRepositoryBridge implements ShoppingBillItemRepository {
    final DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

    public ShoppingBillItemRepositoryBridge() {
        dbProvider.initializeShoppingBillItemTable();
    }

    @Override
    public ShoppingItemVO getItemByID(int ID) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement getItemStatement = connection.prepareStatement("SELECT * FROM shopping_bill_item WHERE id = ?");
        getItemStatement.setInt(1, ID);

        ResultSet itemResult = getItemStatement.executeQuery();
        if (!itemResult.next()) {
            connection.close();
            return null;
        }

        ShoppingItemNameVO itemName = new ShoppingItemNameVO(itemResult.getString("name"));
        ShoppingItemVO item = new ShoppingItemVO(itemResult.getInt("price"), itemName);

        connection.close();
        return item;
    }

    @Override
    public void addItem(ShoppingItemVO item, int shoppingBillID) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement addItemStatement = connection.prepareStatement("INSERT INTO shopping_bill_item (price, name, shopping_bill_id) VALUES (?, ?, ?)");
        addItemStatement.setInt(1, (int) (item.getPrice() * 100));
        addItemStatement.setString(2, item.getItemName().getShoppingItemName());
        addItemStatement.setInt(3, shoppingBillID);

        if (addItemStatement.execute()) {
        }
    }

    @Override
    public List<ShoppingItemVO> getAllItemsForBill(int shoppingBillID) throws SQLException {
        Connection connection = dbProvider.createConnection();

        // get ids
        PreparedStatement idStatement = connection.prepareStatement("SELECT id FROM shopping_bill_item WHERE shopping_bill_id = ?");
        idStatement.setInt(1, shoppingBillID);

        ResultSet entryRes = idStatement.executeQuery();

        List<ShoppingItemVO> result = new ArrayList<>();
        while (entryRes.next()) {
            result.add(getItemByID(entryRes.getInt("id")));
        }

        return result;
    }
}
