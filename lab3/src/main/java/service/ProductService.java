package service;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductService {

    private static final String PRODUCT_ALREADY_EXIST_MESSAGE = "Product with this name already exist!";

    private ProductDao productDao = new ProductDaoImpl();

    public Product getProductById(UUID id) throws SQLException, ClassNotFoundException {
        return productDao.getProductById(id);
    }

    public List<String> saveProduct(String name, String description, Double price) throws SQLException, ClassNotFoundException {
        List<String> errorMessages = new ArrayList<>();

        if (productDao.isProductInDB(name)) {
            errorMessages.add(PRODUCT_ALREADY_EXIST_MESSAGE);
            return errorMessages;
        }

        Product product = new Product()
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setActive(true);

        if (errorMessages.isEmpty()) {
            productDao.addProduct(product);
        }
        return errorMessages;
    }

    public List<String> updateProduct(Product currentProduct, String name, String description, Double price) throws SQLException, ClassNotFoundException {
        List<String> errors = new ArrayList<>();
        if (!currentProduct.getName().equals(name) && productDao.isProductInDB(name)) {
            errors.add(PRODUCT_ALREADY_EXIST_MESSAGE);
            return errors;
        }
        currentProduct.setName(name)
                .setDescription(description)
                .setPrice(price);

        productDao.updateProductById(currentProduct);

        return errors;
    }

    public void changeStatus(UUID id, boolean status) throws SQLException, ClassNotFoundException {
        productDao.updateProductStatusById(id, status);
    }

    public List<Product> getAllActiveProducts() throws SQLException, ClassNotFoundException {
        return productDao.getAllProductsByStatus(true);
    }

    public List<Product> getAllDisabledProducts() throws SQLException, ClassNotFoundException {
        return productDao.getAllProductsByStatus(false);
    }

    public void delete(UUID id) throws SQLException, ClassNotFoundException {
        productDao.deleteProductById(id);
    }
}