package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_arcana_bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnections () throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
