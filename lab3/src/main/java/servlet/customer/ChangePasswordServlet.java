package servlet.customer;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "changePassword", urlPatterns = {"/changePassword"})
public class ChangePasswordServlet extends HttpServlet {

    private UserService service = new UserService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newPass = request.getParameter("newPass");
        String confirmNew = request.getParameter("newConfPass");
        String currentPass = request.getParameter("currentPass");

        List<String> errors;
        try {
            User user = (User) request.getSession().getAttribute("currUser");
            User currUser = service.getUserById(user.getId());
            errors = service.updatePassword(currUser, newPass, confirmNew, currentPass);
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                getServletContext().getRequestDispatcher("/views/customer/Page.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("currUser", service.getUserById(user.getId()));
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }
}

