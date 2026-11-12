package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {


    private static DatabaseConnection instance;
    public Connection databaseLink;

    private DatabaseConnection() {
        String databaseName = "cybermart";
        String databaseUser = "root";
        String databasePassword = "Kakuba@26"; // MAKE SURE THIS IS YOUR PASSWORD
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return databaseLink;
    }
}