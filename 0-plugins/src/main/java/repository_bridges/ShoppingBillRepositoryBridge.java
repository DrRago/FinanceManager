package repository_bridges;

import entities.ShoppingBillEntity;
import repositories.ShoppingBillRepository;
import services.DatabaseConnectionProviderService;
import value_objects.DateVO;
import value_objects.ShopNameVO;
import value_objects.ShoppingItemVO;
import value_objects.UsernameVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBillRepositoryBridge implements ShoppingBillRepository {
    DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

    public ShoppingBillRepositoryBridge() {
        dbProvider.initializeShoppingBillTable();
    }

    @Override
    public ShoppingBillEntity addShoppingBill(ShopNameVO shopName, DateVO date, UsernameVO user, List<ShoppingItemVO> items) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement shoppingBillAddStatement = connection.prepareStatement("INSERT INTO shopping_bill (shopName, date, user) VALUES (?, ?, ?)");
        shoppingBillAddStatement.setString(1, shopName.getShopName());
        shoppingBillAddStatement.setString(2, date.getDateSting());
        shoppingBillAddStatement.setString(3, user.getUsername());

        shoppingBillAddStatement.execute();

        // get id
        Statement idStatement = connection.createStatement();
        idStatement.execute("SELECT last_insert_rowid()");
        int id = idStatement.getResultSet().getInt(1);

        // add items
        ShoppingBillItemRepositoryBridge itemRepo = new ShoppingBillItemRepositoryBridge();
        for (ShoppingItemVO item : items) {
            itemRepo.addItem(item, id);
        }

        connection.close();
        return new ShoppingBillEntity(id, shopName, date, user, items);
    }

    @Override
    public void removeEntryByID(int ID) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement removeEntryStatement = connection.prepareStatement("DELETE FROM shopping_bill WHERE id = ?");
        removeEntryStatement.setInt(1, ID);
        removeEntryStatement.execute();

        PreparedStatement removeItemStatement = connection.prepareStatement("DELETE FROM shopping_bill_item WHERE shopping_bill_id = ?");
        removeItemStatement.setInt(1, ID);
        removeItemStatement.execute();

        connection.close();
    }

    @Override
    public ShoppingBillEntity getEntryByID(int ID) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement getEntryStatement = connection.prepareStatement("SELECT * FROM shopping_bill WHERE id = ?");
        getEntryStatement.setInt(1, ID);

        ResultSet entryRes = getEntryStatement.executeQuery();
        if (!entryRes.next()) {
            connection.close();
            return null;
        }
        ShopNameVO shopName = new ShopNameVO(entryRes.getString("shopName"));
        DateVO date = new DateVO(entryRes.getString("date"));
        UsernameVO user = new UsernameVO(entryRes.getString("user"));

        ShoppingBillItemRepositoryBridge itemRepo = new ShoppingBillItemRepositoryBridge();

        connection.close();

        return new ShoppingBillEntity(ID, shopName, date, user, itemRepo.getAllItemsForBill(ID));
    }

    public List<ShoppingBillEntity> getAllShoppingBillsForUser(UsernameVO user) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement getEntriesStatement = connection.prepareStatement("SELECT * FROM shopping_bill WHERE user = ? ORDER BY date DESC ");
        getEntriesStatement.setString(1, user.getUsername());

        ResultSet entryRes = getEntriesStatement.executeQuery();

        List<ShoppingBillEntity> result = new ArrayList<>();
        while (entryRes.next()) {
            result.add(getEntryByID(entryRes.getInt("id")));
        }

        return result;
    }
}
