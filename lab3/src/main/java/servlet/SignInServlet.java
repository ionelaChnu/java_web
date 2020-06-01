package servlet;

import model.Order;
import model.User;
import model.dto.CartInfoDTO;
import service.OrderService;
import service.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "signIn", urlPatterns = {"/signIn"})
public class SignInServlet extends HttpServlet {

    private UserService service = new UserService();
    private OrderService orderService = new OrderService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/SignIn.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String loginError;

        try {
            loginError = service.loginUserByPassword(login, password);
            if (loginError != null) {
                request.setAttribute("loginError", loginError);
                request.setAttribute("login", login);
                request.getRequestDispatcher("/views/SignIn.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                User user = service.getUserByLogin(login);
                session.setAttribute("currUser", user);
                session.setAttribute("cart", CartInfoDTO.makeDTO(orderService.getActiveOrderByUser(user.getId())));
                session.setMaxInactiveInterval(2000);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}