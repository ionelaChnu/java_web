package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface UserDao {
    User getUserById(UUID id) throws ClassNotFoundException, SQLException;
    User getUserByLogin(String login) throws ClassNotFoundException, SQLException;
    boolean isUserActive(String login) throws SQLException, ClassNotFoundException;
    boolean isUserInDB(String login) throws SQLException, ClassNotFoundException;
    UUID addUser(User user) throws ClassNotFoundException, SQLException;
    void updateUserStatusById(UUID id, boolean status) throws ClassNotFoundException, SQLException;
    void deleteUserById(UUID id) throws ClassNotFoundException, SQLException;
    void updateUserById(User user) throws ClassNotFoundException, SQLException;
    List<User> getAllUsersByStatus(boolean status) throws ClassNotFoundException, SQLException;
}
