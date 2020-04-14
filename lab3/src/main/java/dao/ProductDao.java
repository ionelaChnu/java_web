package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ProductDao {
    Product getProductById(UUID id) throws ClassNotFoundException, SQLException;
    boolean isProductActive(UUID id) throws SQLException, ClassNotFoundException;
    boolean isProductInDB(String name) throws SQLException, ClassNotFoundException;
    UUID addProduct(Product product) throws ClassNotFoundException, SQLException;
    void updateProductStatusById(UUID id, boolean status) throws ClassNotFoundException, SQLException;
    void deleteProductById(UUID id) throws ClassNotFoundException, SQLException;
    void updateProductById(Product product) throws ClassNotFoundException, SQLException;
    List<Product> getAllProducts() throws ClassNotFoundException, SQLException;
    List<Product> getAllProductsByStatus(boolean status) throws ClassNotFoundException, SQLException;
}
