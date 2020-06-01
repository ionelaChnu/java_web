package dao.impl;

import dao.UserDao;
import database.DataBaseConnection;
import model.User;
import model.enums.Authority;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(UUID id) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setAuthority(Authority.valueOf(resultSet.getString("authority")))
                    .setActive(resultSet.getBoolean("is_active"));
        }
        conn.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM users WHERE login = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setObject(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setAuthority(Authority.valueOf(resultSet.getString("authority")))
                    .setActive(resultSet.getBoolean("is_active"));
        }
        conn.close();
        return user;
    }

    @Override
    public boolean isUserActive(String login) throws SQLException, ClassNotFoundException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM users WHERE login = ? AND is_active = TRUE ";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setObject(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            conn.close();
            return true;
        } else {
            conn.close();
            return false;
        }
    }

    @Override
    public boolean isUserInDB(String login) throws SQLException, ClassNotFoundException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM users WHERE login = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setObject(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            conn.close();
            return true;
        } else {
            conn.close();
            return false;
        }
    }

    @Override
    public UUID addUser(User user) throws ClassNotFoundException, SQLException {
        if (user.getId() == null) {
            Connection conn = DataBaseConnection.getNewConnection();
            Statement st = conn.createStatement();
            String preparedQuery = "INSERT INTO users (first_name,last_name,login,password,authority,is_active) VALUES(?,?,?,?,?,TRUE)";
            PreparedStatement preparedStmt = conn.prepareStatement(preparedQuery);
            preparedStmt.setString(1, user.getFirstName());
            preparedStmt.setString(2, user.getLastName());
            preparedStmt.setString(3, user.getLogin());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, Authority.CUSTOMER.toString());
            preparedStmt.executeUpdate();

            String preparedQuery1 = "SELECT id FROM users WHERE login=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(preparedQuery1);
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            UUID uuid = null;
            if (resultSet.next()) {
                uuid = (UUID.fromString(resultSet.getString("id")));
            }
            conn.close();
            return uuid;
        } else
            return user.getId();
    }

    @Override
    public void updateUserStatusById(UUID id, boolean status) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE users SET is_active=? " +
                " WHERE id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setBoolean(1, status);
        preparedStatement.setString(2, id.toString());
        int res = preparedStatement.executeUpdate();
        conn.close();
        if (res == 0) {
            throw new IllegalArgumentException(id.toString() + " not found");
        }
    }

    @Override
    public void deleteUserById(UUID id) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepDelete = "DELETE FROM users WHERE id= ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepDelete);
        preparedStatement.setObject(1, id);
        int rs = preparedStatement.executeUpdate();
        if (rs == 0) {
            conn.close();
            throw new IllegalArgumentException("User with id: " + id + " not found");
        }
        conn.close();
    }

    @Override
    public void updateUserById(User user) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE users SET login=?,password=?, first_name=?, last_name=?" +
                " WHERE id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getId().toString());
        int res = preparedStatement.executeUpdate();
        conn.close();
        if (res == 0) {
            throw new IllegalArgumentException(user.toString() + " not found");
        }
    }

    @Override
    public List<User> getAllUsersByStatus(boolean status) throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM users WHERE is_active = ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setObject(1, status);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setAuthority(Authority.valueOf(resultSet.getString("authority")))
                    .setActive(resultSet.getBoolean("is_active"));
            users.add(user);
        }
        conn.close();
        return users;
    }
}
