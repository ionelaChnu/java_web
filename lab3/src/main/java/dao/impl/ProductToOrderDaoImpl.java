package dao.impl;

import dao.ProductDao;
import dao.ProductToOrderDao;
import database.DataBaseConnection;
import model.ProductToOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProductToOrderDaoImpl implements ProductToOrderDao {
    @Override
    public void addProductToOrder(ProductToOrder productToOrder, UUID orderId) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String preparedQuery = "INSERT INTO product_to_order (product_id,order_id,amount,quantity) VALUES(?,?,?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(preparedQuery);
        preparedStmt.setString(1, productToOrder.getProduct().getId().toString());
        preparedStmt.setString(2, orderId.toString());
        preparedStmt.setDouble(3, productToOrder.getAmount());
        preparedStmt.setInt(4, productToOrder.getQuantity());
        preparedStmt.executeUpdate();
        conn.close();
    }

    @Override
    public void deleteAllProductToOrderByOrderId(UUID id) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepDelete = "DELETE FROM product_to_order WHERE order_id= ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepDelete);
        preparedStatement.setString(1, id.toString());
        int rs = preparedStatement.executeUpdate();
        conn.close();
    }

    @Override
    public void updateProductToOrderByProductAndOrderId(ProductToOrder productToOrder, UUID orderId) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE product_to_order SET amount=?,quantity=?" +
                " WHERE product_id=? AND order_id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setDouble(1, productToOrder.getAmount());
        preparedStatement.setInt(2, productToOrder.getQuantity());
        preparedStatement.setString(3, productToOrder.getProduct().getId().toString());
        preparedStatement.setString(4, orderId.toString());
        int res = preparedStatement.executeUpdate();
        conn.close();
        if (res == 0) {
            throw new IllegalArgumentException(productToOrder.toString() + " not found");
        }
    }

    @Override
    public void deleteProductToOrderByOrderAndProductId(UUID id, UUID productId) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepDelete = "DELETE FROM product_to_order WHERE order_id= ? AND product_id=?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepDelete);
        preparedStatement.setString(1, id.toString());
        preparedStatement.setString(2, productId.toString());
        int rs = preparedStatement.executeUpdate();
        conn.close();
    }

    @Override
    public Set<ProductToOrder> getAllOrdersByOrderId(UUID id) throws ClassNotFoundException, SQLException {
        ProductDao productDao = new ProductDaoImpl();
        Set<ProductToOrder> products = new HashSet<>();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product_to_order WHERE order_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ProductToOrder productToOrder = new ProductToOrder()
                    .setAmount(resultSet.getDouble("amount"))
                    .setQuantity(resultSet.getInt("quantity"))
                    .setProduct(productDao.getProductById(UUID.fromString(resultSet.getString("product_id"))));
            products.add(productToOrder);
        }
        conn.close();
        return products;
    }
}
