import org.junit.jupiter.api.Test;
import services.DatabaseConnectionProviderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionProviderServiceTest {
    @Test
    public void createConnection_getActiveConnection() throws SQLException {
        DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

        Connection connection = dbProvider.createConnection();
        assertFalse(connection.isClosed());

        connection.close();
        assertTrue(connection.isClosed());
    }

    private boolean checkIfTableExists(String tableName, DatabaseConnectionProviderService dbProvider) throws SQLException {
        Connection connection = dbProvider.createConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' AND name=?");
        statement.setString(1, tableName);

        ResultSet rs = statement.executeQuery();
        if (!rs.next())
            return false;

        String queriedName = rs.getString(1);
        connection.close();

        return queriedName != null && !queriedName.isEmpty();
    }

    @Test
    public void initializeUserTable_createUserTableDefinition() throws SQLException {
        DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

        dbProvider.initializeUserTable();
        assertTrue(checkIfTableExists("user", dbProvider));
    }

    @Test
    public void initializeShoppingBillTable_createShoppingBillTableDefinition() throws SQLException {
        DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

        dbProvider.initializeShoppingBillTable();
        assertTrue(checkIfTableExists("shopping_bill", dbProvider));
    }

    @Test
    public void initializeShoppingBillItemTable_createShoppingBillItemTableDefinition() throws SQLException {
        DatabaseConnectionProviderService dbProvider = new DatabaseConnectionProviderService();

        dbProvider.initializeShoppingBillItemTable();
        assertTrue(checkIfTableExists("shopping_bill_item", dbProvider));
    }
}
