package servlet.admin;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "changeStatus", urlPatterns = {"/changeStatus"})
public class ChangeUserStatusServlet extends HttpServlet {

    private UserService userService = new UserService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("idUser"));
        User user = (User) request.getSession().getAttribute("currUser");
        if (id.equals(user.getId())) {
            request.setAttribute("message", "You cannot block yourself");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
            return;
        }
        boolean status = Integer.parseInt(request.getParameter("status")) != 1;

        try {
            userService.changeUserStatus(id, status);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }

    }
}
