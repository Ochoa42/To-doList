package DataBase;

import Utils.PasswordMysql;
import Utils.UserMysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ToDoList";
    private static final String DB_USERNAME = UserMysql.userDataBase;
    private static final String DB_PASSWORD = PasswordMysql.passwordDataBase;
    private DataBaseConnection() {}
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish database connection", e);
        }
    }

    public static void main(String[] args){
        Connection connection = getConnection();
    }
}
