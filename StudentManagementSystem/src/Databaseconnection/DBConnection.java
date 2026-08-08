package Databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    private static final String url ="jdbc:mysql://localhost:3306/StudentManagementDB";
    private static final String user = "root";
    private static final String password = "12345";
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}