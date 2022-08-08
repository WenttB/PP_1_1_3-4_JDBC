package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String conURL = "jdbc:mysql://localhost:3306/task";
    private static String userName = "root";
    private static String userPassword = "1408";
    private static Connection connection;

    public static Connection getMYSQLConnection() {

        try {
            connection = DriverManager.getConnection(conURL, userName, userPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection () {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
