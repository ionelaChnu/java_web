package service;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import model.Order;
import model.enums.OrderStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    public Optional<Order> getActiveOrderByUser(UUID id) throws SQLException, ClassNotFoundException {
        return Optional.ofNullable(orderDao.getActiveOrderByUserId(id));
    }

    public UUID createOrder(Order order) throws SQLException, ClassNotFoundException {
        return orderDao.addOrder(order);
    }

    public void completeOrder(UUID id) throws SQLException, ClassNotFoundException {
        Optional<Order> optionalOrder = getActiveOrderByUser(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get().setStatus(OrderStatus.WAITING_FOR_PAYMENT);
            orderDao.updateOrderStatus(order);
        }
    }

    public Set<Order> getAllUserOrders(UUID id) throws SQLException, ClassNotFoundException {
        return orderDao.getAllOrdersByUserId(id);
    }
}