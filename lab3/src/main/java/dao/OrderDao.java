package dao;

import model.Order;
import model.Product;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public interface OrderDao {
    Order getOrderById(UUID id) throws ClassNotFoundException, SQLException;
    UUID addOrder(Order order) throws ClassNotFoundException, SQLException;
    void deleteOrderById(UUID id) throws ClassNotFoundException, SQLException;
    void updateOrderStatus(Order order) throws ClassNotFoundException, SQLException;
    Set<Order> getAllOrdersByUserId(UUID id) throws ClassNotFoundException, SQLException;
    Order getActiveOrderByUserId(UUID id) throws ClassNotFoundException, SQLException;
}
