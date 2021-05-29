package domain_services;

import java.sql.Connection;

public interface DatabaseConnectionProviderDomainService {
    Connection getConnection();

    Connection createConnection();

    void initializeUserTable();

    void initializeShoppingBillTable();

    void initializeShoppingBillItemTable();

    void initializeTables();

    void close();
}