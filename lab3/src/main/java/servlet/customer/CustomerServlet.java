package servlet.customer;

import model.Product;
import model.User;
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

@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {

    private UserService service = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/customer/Page.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        List<String> errors = new ArrayList<>();
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            User currUser = service.getUserById(user.getId());
            errors = service.updateUserInfo(currUser, firstName, lastName, login);
            if (!errors.isEmpty()) {
                request.setAttribute("registerError", errors);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("login", login);
                getServletContext().getRequestDispatcher("/views/customer/Page.jsp").forward(request, response);
                return;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("currUser", service.getUserByLogin(login));
                request.setAttribute("currUser", service.getUserByLogin(login));
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPass = request.getParameter("currentPass");
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            User currUser = service.getUserById(user.getId());
            service.delete(currUser, currentPass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    }
}
