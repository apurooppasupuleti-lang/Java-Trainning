package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class DBManager {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/practice";
    public static final String USER = "postgres";
    public static final String Password = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, Password);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                connection=null;

            } catch (SQLException e) {
                System.out.println("Error closing connection: "+ e.getErrorCode());
            }
        }
    }
}

