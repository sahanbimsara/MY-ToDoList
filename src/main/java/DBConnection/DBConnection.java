package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String URL = "jdbc:mysql://localhost:3306/todolist";
    private static String USER = "root";
    private static String PASSWORD = "sahan2004";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
