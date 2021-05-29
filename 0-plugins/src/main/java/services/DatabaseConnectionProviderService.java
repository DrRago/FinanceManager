package services;

import domain_services.DatabaseConnectionProviderDomainService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionProviderService implements DatabaseConnectionProviderDomainService {
    Connection connection;

    public DatabaseConnectionProviderService() {
        createConnection();
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Connection createConnection() {
        // prepare database
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    @Override
    public void initializeUserTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table if not exists user (id TEXT constraint user_pk primary key, username TEXT, password TEXT not null)");
            statement.executeUpdate("create unique index if not exists user_username_uindex on user (username)");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void initializeShoppingBillTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table if not exists shopping_bill (id integer constraint shopping_bill_entity_pk primary key autoincrement, shopName TEXT not null)");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void initializeShoppingBillItemTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table if not exists shopping_bill_item (id integer constraint shopping_bill_item_pk primary key autoincrement, price real not null, name TEXT not null, shopping_bill_id int references shopping_bill on update cascade on delete cascade)");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void initializeTables() {
        initializeUserTable();
        initializeShoppingBillTable();
        initializeShoppingBillItemTable();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
