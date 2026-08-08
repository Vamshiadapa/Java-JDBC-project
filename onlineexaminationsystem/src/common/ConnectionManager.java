package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String HOST = "jdbc:mysql://localhost:3306/exam_db";
    private static final String USER = "root";
    private static final String PASS = "12345";

    private static Connection connection;

    public static Connection get() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(HOST, USER, PASS);
            } catch (ClassNotFoundException e) {
                throw new SQLException("mysql jdbc driver not found on classpath", e);
            }
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}