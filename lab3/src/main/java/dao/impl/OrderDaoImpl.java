package dao.impl;

import dao.OrderDao;
import dao.ProductToOrderDao;
import database.DataBaseConnection;
import model.Order;
import model.ProductToOrder;
import model.enums.OrderStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order getOrderById(UUID id) throws ClassNotFoundException, SQLException {
        ProductToOrderDao productToOrderDao = new ProductToOrderDaoImpl();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM `order` WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        Order order = null;
        if (resultSet.next()) {
            order = new Order()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setDate((resultSet.getObject("date", LocalDateTime.class)))
                    .setStatus(OrderStatus.valueOf(resultSet.getString("status")));
            Set<ProductToOrder> productToOrders = productToOrderDao.getAllOrdersByOrderId(order.getId());
            order.setItems(productToOrders);
        }
        conn.close();
        return order;
    }

    @Override
    public UUID addOrder(Order order) throws ClassNotFoundException, SQLException {
        if (order.getId() == null) {
            Connection conn = DataBaseConnection.getNewConnection();
            Statement st = conn.createStatement();
            String preparedQuery = "INSERT INTO `order` (date,user_id,status) VALUES(?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(preparedQuery);
            preparedStmt.setObject(1, LocalDateTime.now());
            preparedStmt.setString(2, order.getUser().getId().toString());
            preparedStmt.setString(3, order.getStatus().toString());
            preparedStmt.executeUpdate();

            String preparedQuery1 = "SELECT id FROM `order` WHERE status='ACTIVE'";
            PreparedStatement preparedStatement = conn.prepareStatement(preparedQuery1);
            ResultSet resultSet = preparedStatement.executeQuery();
            UUID uuid = null;
            if (resultSet.next()) {
                uuid = (UUID.fromString(resultSet.getString("id")));
            }
            return uuid;
        } else return order.getId();
    }

    @Override
    public void deleteOrderById(UUID id) throws ClassNotFoundException, SQLException {
        ProductToOrderDao productToOrderDao = new ProductToOrderDaoImpl();
        productToOrderDao.deleteAllProductToOrderByOrderId(id);
        Connection conn = DataBaseConnection.getNewConnection();
        String prepDelete = "DELETE FROM `order` WHERE id= ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepDelete);
        preparedStatement.setString(1, id.toString());
        int rs = preparedStatement.executeUpdate();
        conn.close();
        if (rs == 0) {
            throw new IllegalArgumentException("Order with id: " + id + " not found");
        }
    }

    @Override
    public void updateOrderStatus(Order order) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE `order` SET status=? " +
                " WHERE id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setString(1, order.getStatus().toString());
        preparedStatement.setString(2, order.getId().toString());
        preparedStatement.executeUpdate();
        conn.close();
    }

    @Override
    public Set<Order> getAllOrdersByUserId(UUID id) throws ClassNotFoundException, SQLException {
        ProductToOrderDao productToOrderDao = new ProductToOrderDaoImpl();
        Set<Order> products = new HashSet<>();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM `order` WHERE user_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        conn.close();
        while (resultSet.next()) {
            Order order = new Order()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setDate((resultSet.getObject("date", LocalDateTime.class)))
                    .setStatus(OrderStatus.valueOf(resultSet.getString("status")));
            Set<ProductToOrder> productToOrders = productToOrderDao.getAllOrdersByOrderId(order.getId());
            order.setItems(productToOrders);
            products.add(order);
        }
        return products;
    }

    @Override
    public Order getActiveOrderByUserId(UUID id) throws ClassNotFoundException, SQLException {
        ProductToOrderDao productToOrderDao = new ProductToOrderDaoImpl();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM `order` WHERE user_id = ? AND status='ACTIVE'";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        Order order = null;
        if (resultSet.next()) {
            order = new Order()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setDate((resultSet.getObject("date", LocalDateTime.class)))
                    .setStatus(OrderStatus.valueOf(resultSet.getString("status")));
            Set<ProductToOrder> productToOrders = productToOrderDao.getAllOrdersByOrderId(order.getId());
            order.setItems(productToOrders);
        }
        conn.close();
        return order;
    }
}
