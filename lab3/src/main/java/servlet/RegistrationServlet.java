package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "registration", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private UserService service = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/NewUser.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        List<String> errors;
        try {
            errors = service.registerUser(firstName, lastName, login, password, confirmPassword);
            if (!errors.isEmpty()) {
                request.setAttribute("registerError", errors);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("login", login);
                getServletContext().getRequestDispatcher("/views/NewUser.jsp").forward(request, response);
                return;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("currUser", service.getUserByLogin(login));
                request.setAttribute("currUser", service.getUserByLogin(login));
                session.setMaxInactiveInterval(2000);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}
