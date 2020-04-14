package dao;

import model.ProductToOrder;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;

public interface ProductToOrderDao {
    void addProductToOrder(ProductToOrder productToOrder, UUID orderId) throws ClassNotFoundException, SQLException;
    void deleteAllProductToOrderByOrderId(UUID id) throws ClassNotFoundException, SQLException;
    void updateProductToOrderByProductAndOrderId(ProductToOrder productToOrder, UUID orderId) throws ClassNotFoundException, SQLException;
    void deleteProductToOrderByOrderAndProductId(UUID id, UUID productId) throws ClassNotFoundException, SQLException;
    Set<ProductToOrder> getAllOrdersByOrderId(UUID id) throws ClassNotFoundException, SQLException;
}
