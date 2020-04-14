package dao.impl;

import dao.ProductDao;
import database.DataBaseConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product getProductById(UUID id) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        Product product = null;
        if (resultSet.next()) {
            product = new Product()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setName(resultSet.getString("name"))
                    .setActive(resultSet.getBoolean("is_active"))
                    .setDescription(resultSet.getString("description"))
                    .setPrice(resultSet.getDouble("price"));
        }
        conn.close();
        return product;
    }

    @Override
    public boolean isProductActive(UUID id) throws SQLException, ClassNotFoundException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product WHERE id = ? AND is_active=TRUE ";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setString(1, id.toString());
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
    public boolean isProductInDB(String name) throws SQLException, ClassNotFoundException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product WHERE name = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setObject(1, name);
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
    public UUID addProduct(Product product) throws ClassNotFoundException, SQLException {
        if (product.getId() == null) {
            Connection conn = DataBaseConnection.getNewConnection();
            Statement st = conn.createStatement();
            String preparedQuery = "INSERT INTO product (name,price,description, is_active) VALUES(?,?,?,TRUE)";
            PreparedStatement preparedStmt = conn.prepareStatement(preparedQuery);
            preparedStmt.setString(1, product.getName());
            preparedStmt.setDouble(2, product.getPrice());
            preparedStmt.setString(3, product.getDescription());
            preparedStmt.executeUpdate();

            String preparedQuery1 = "SELECT id FROM product WHERE name=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(preparedQuery1);
            preparedStatement.setString(1, product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            UUID uuid = null;
            if (resultSet.next()) {
                uuid = UUID.fromString(resultSet.getString("id"));
            }
            conn.close();
            return uuid;
        } else return product.getId();
    }

    @Override
    public void updateProductStatusById(UUID id, boolean status) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE product SET is_active=? " +
                " WHERE id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setBoolean(1, status);
        preparedStatement.setString(2, id.toString());
        preparedStatement.executeUpdate();
        conn.close();
    }

    @Override
    public void deleteProductById(UUID id) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepDelete = "DELETE FROM product WHERE id= ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(prepDelete);
        preparedStatement.setString(1, id.toString());
        int rs = preparedStatement.executeUpdate();
        conn.close();
        if (rs == 0) {
            throw new IllegalArgumentException("Product with id: " + id + " not found");
        }
    }

    @Override
    public void updateProductById(Product product) throws ClassNotFoundException, SQLException {
        Connection conn = DataBaseConnection.getNewConnection();
        String prepUpdate = "UPDATE product SET name=?,price=?, description=?" +
                " WHERE id=?;";

        PreparedStatement preparedStatement = conn.prepareStatement(prepUpdate);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setString(4, product.getId().toString());
        int res = preparedStatement.executeUpdate();
        conn.close();
        if (res == 0) {
            throw new IllegalArgumentException(product.toString() + " not found");
        }
    }

    @Override
    public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product ";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setName(resultSet.getString("name"))
                    .setActive(resultSet.getBoolean("is_active"))
                    .setDescription(resultSet.getString("description"))
                    .setPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        conn.close();
        return products;
    }

    @Override
    public List<Product> getAllProductsByStatus(boolean status) throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = DataBaseConnection.getNewConnection();
        String prepQuery = "SELECT * FROM product WHERE is_active=?";
        PreparedStatement preparedStatement = conn.prepareStatement(prepQuery);
        preparedStatement.setBoolean(1, status);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product()
                    .setId(UUID.fromString(resultSet.getString("id")))
                    .setName(resultSet.getString("name"))
                    .setActive(resultSet.getBoolean("is_active"))
                    .setDescription(resultSet.getString("description"))
                    .setPrice(resultSet.getDouble("price"));
            products.add(product);
        }
        conn.close();
        return products;
    }
}
