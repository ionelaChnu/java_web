package db;

import java.util.Properties;

public class PropertyManager {

    private PropertyManager() {
    }

    public static Properties getProperties() {

        Properties props = new Properties();
        props.put("jdbc.url", "jdbc:postgresql://localhost:5433/lab2_db");
        props.put("user", "postgres");
        props.put("password", "root");
        return props;
    }
}
