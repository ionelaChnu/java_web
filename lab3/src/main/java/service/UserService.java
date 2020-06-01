package service;

import dao.ProductToOrderDao;
import dao.UserDao;
import dao.impl.ProductToOrderDaoImpl;
import dao.impl.UserDaoImpl;
import encryption.PasswordUtils;
import model.Order;
import model.Product;
import model.ProductToOrder;
import model.User;
import model.enums.Authority;
import model.enums.OrderStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {

    private static final String USER_ALREADY_EXIST_MESSAGE = "User with this login already exist!";
    private static final String PASSWORDS_NOT_EQUAL = "Passwords are not equal!";

    private UserDao userDao = new UserDaoImpl();
    private ProductToOrderDao productToOrderDao = new ProductToOrderDaoImpl();
    private ProductService productService = new ProductService();
    private OrderService orderService = new OrderService();

    public String loginUserByPassword(String login, String password) throws SQLException, ClassNotFoundException {
        User user = userDao.getUserByLogin(login);
        System.out.println(user);
        if (!userDao.isUserActive(login) || user == null || !PasswordUtils.verifyUserPassword(password, user.getPassword())) {
            return "Login or password is incorrect!";
        }
        return null;
    }

    public User getUserByLogin(String login) throws SQLException, ClassNotFoundException {
        return userDao.getUserByLogin(login);
    }

    public User getUserById(UUID id) throws SQLException, ClassNotFoundException {
        return userDao.getUserById(id);
    }

    public void changeUserStatus(UUID id, boolean status) throws SQLException, ClassNotFoundException {
        userDao.updateUserStatusById(id, status);
    }

    public List<String> registerUser(String firstName, String lastName, String login, String password, String confirmPassword) throws SQLException, ClassNotFoundException {
        List<String> errorMessages = new ArrayList<>();
        if (!password.equals(confirmPassword)) {
            errorMessages.add(PASSWORDS_NOT_EQUAL);
            return errorMessages;
        }

        if (loginAlreadyExists(login)) {
            errorMessages.add(USER_ALREADY_EXIST_MESSAGE);
            return errorMessages;
        }

        User user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(login)
                .setPassword(PasswordUtils.generateSecurePassword(password, Optional.empty()))
                .setActive(true)
                .setAuthority(Authority.CUSTOMER);

        if (errorMessages.isEmpty()) {
            userDao.addUser(user);
        }
        return errorMessages;
    }

    private boolean loginAlreadyExists(String login) throws SQLException, ClassNotFoundException {
        return userDao.isUserInDB(login);
    }

    public List<String> updateUserInfo(User currentUser, String firstName, String lastName,
                                       String login) throws SQLException, ClassNotFoundException {
        List<String> errors = new ArrayList<>();
        if (!currentUser.getLogin().equals(login) && loginAlreadyExists(login)) {
            errors.add(USER_ALREADY_EXIST_MESSAGE);
            return errors;
        }
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setLogin(login);
        userDao.updateUserById(currentUser);

        return errors;
    }

    public List<User> getAllActiveUsers() throws SQLException, ClassNotFoundException {
        List<User> activeUsers = userDao.getAllUsersByStatus(true);
        return activeUsers;
    }

    public List<User> getAllDisabledUsers() throws SQLException, ClassNotFoundException {
        List<User> disabledUsers = userDao.getAllUsersByStatus(false);
        return disabledUsers;
    }

    public List<String> updatePassword(User currentUser, String newPassword, String confirmNew, String currentPassword) throws SQLException, ClassNotFoundException {
        List<String> errors = new ArrayList<>();

        if (!newPassword.equals(confirmNew)) {
            errors.add("Passwords do not match!!!");
            return errors;
        } else if (!PasswordUtils.verifyUserPassword(currentPassword, currentUser.getPassword())) {
            errors.add("Current password is wrong!");
            return errors;
        }

        currentUser.setPassword(PasswordUtils.generateSecurePassword(newPassword, Optional.empty()));

        if (!errors.isEmpty()) {
            return errors;
        }
        userDao.updateUserById(currentUser);

        return errors;
    }

    public List<String> delete(User currentUser, String currentPassword) throws SQLException, ClassNotFoundException {
        List<String> errors = new ArrayList<>();

        if (!PasswordUtils.verifyUserPassword(currentPassword, currentUser.getPassword())) {
            errors.add("Current password is wrong!");
            return errors;
        }
        userDao.updateUserStatusById(currentUser.getId(), false);
        return errors;
    }

    public void addProductToCart(UUID productId, int quantity, UUID userId) throws SQLException, ClassNotFoundException {
        User user = getUserById(userId);
        Product product = productService.getProductById(productId);
        Optional<Order> optionalOrder = orderService.getActiveOrderByUser(userId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            Optional<ProductToOrder> optionalProductToOrder = order.getItems()
                    .stream()
                    .filter(v -> v.getProduct().equals(product))
                    .findFirst();
            if (optionalProductToOrder.isPresent()) {
                ProductToOrder productToOrder = optionalProductToOrder.get();
                int newQuantity = productToOrder.getQuantity() + quantity;
                productToOrder.setQuantity(newQuantity)
                        .setAmount(product.getPrice() * newQuantity);
                productToOrderDao.updateProductToOrderByProductAndOrderId(productToOrder, order.getId());
            } else {
                ProductToOrder productToOrder = new ProductToOrder()
                        .setProduct(product)
                        .setQuantity(quantity)
                        .setAmount(quantity * product.getPrice());
                productToOrderDao.addProductToOrder(productToOrder, order.getId());
            }
        } else {
            Order order = new Order()
                    .setStatus(OrderStatus.ACTIVE)
                    .setUser(user);
            UUID orderUUID = orderService.createOrder(order);
            ProductToOrder productToOrder = new ProductToOrder()
                    .setProduct(product)
                    .setQuantity(quantity)
                    .setAmount(quantity * product.getPrice());
            productToOrderDao.addProductToOrder(productToOrder, orderUUID);
        }
    }

    public void deleteProductFromCart(UUID userId, UUID prodId) throws SQLException, ClassNotFoundException {
        Optional<Order> optionalOrder = orderService.getActiveOrderByUser(userId);
        productToOrderDao.deleteProductToOrderByOrderAndProductId(optionalOrder.get().getId(), prodId);
    }
}