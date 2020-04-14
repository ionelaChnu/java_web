package database;

import java.sql.*;
import java.util.Properties;

public class DataBaseConnection {
    private Connection connection;

    public void connectDatabase() {

        Properties props = PropertyManager.getProperties();
        String url = props.getProperty("jdbc.url");

        try {
            connection = DriverManager.getConnection(url, props);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties properties = PropertyManager.getProperties();
        String url = properties.getProperty("jdbc.url");
        return DriverManager.getConnection(url, properties);
    }
    private void disconnectDatabase() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    public void close() {
        disconnectDatabase();
    }
}
