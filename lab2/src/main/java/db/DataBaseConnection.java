package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private Connection connection;

    public void connectDatabase() {

        Properties props = PropertyManager.getProperties();
        String url = props.getProperty("jdbc.url");

        try {
            connection = DriverManager.getConnection(url, props);

        } catch (SQLException e) {
        }
    }

    public static Connection getNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties properties = PropertyManager.getProperties();
        String url = properties.getProperty("jdbc.url");
        return DriverManager.getConnection(url, properties);
    }
    private void disconnectDatabase() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
            }
    }
    public void close() {
        disconnectDatabase();
    }
}
