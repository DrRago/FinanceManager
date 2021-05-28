package repository_bridges;

import entities.ShoppingBillEntity;
import entities.ShoppingItemEntity;
import repositories.ShoppingBillRepository;
import value_objects.ShopNameVO;
import value_objects.UuidVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ShoppingBillRepositoryBridge implements ShoppingBillRepository {
    Connection connection = null;

    public ShoppingBillRepositoryBridge() {
        // prepare database
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("create table if not exists shopping_bill (id integer constraint shopping_bill_entity_pk primary key autoincrement, shopName TEXT not null)");
            statement.executeUpdate("create table if not exists shopping_bill_item (id integer constraint shopping_bill_item_pk primary key autoincrement, price real not null, name TEXT not null, shopping_bill_id int references shopping_bill on update cascade on delete cascade)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

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
