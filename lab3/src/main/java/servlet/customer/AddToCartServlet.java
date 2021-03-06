package servlet.customer;

import model.User;
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
import java.util.UUID;

@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {

    private UserService service = new UserService();
    private OrderService orderService = new OrderService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID productId = UUID.fromString(request.getParameter("pId"));
        int productQuantity = Integer.valueOf(request.getParameter("pQuantity"));
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            service.addProductToCart(productId, productQuantity, user.getId());
            HttpSession session = request.getSession();
            session.setAttribute("cart", CartInfoDTO.makeDTO(orderService.getActiveOrderByUser(user.getId())));
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/userProducts"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
