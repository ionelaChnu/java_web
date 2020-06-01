package servlet.customer;

import model.User;
import model.dto.CartDTO;
import model.dto.CartInfoDTO;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private UserService service = new UserService();
    private OrderService orderService = new OrderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currUser");
        try {
            request.setAttribute("cartInfo", CartDTO.makeDTO(orderService.getActiveOrderByUser(user.getId())));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/views/customer/Cart.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID productId = UUID.fromString(request.getParameter("prodId"));
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            service.deleteProductFromCart(user.getId(), productId);
            HttpSession session = request.getSession();
            session.setAttribute("cart", CartInfoDTO.makeDTO(orderService.getActiveOrderByUser(user.getId())));
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
