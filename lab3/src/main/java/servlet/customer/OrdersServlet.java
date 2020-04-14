package servlet.customer;

import model.Order;
import model.Product;
import model.User;
import model.dto.OrderDetailsDTO;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "orders", urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {

    private OrderService service = new OrderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            Set<Order> orders = service.getAllUserOrders(user.getId());
            request.setAttribute("orders", orders.stream().map(OrderDetailsDTO::makeOrderDetailsDTO).collect(Collectors.toList()));

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        getServletContext().getRequestDispatcher("/views/customer/UserOrders.jsp").forward(request, response);
    }
}
