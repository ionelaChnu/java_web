package servlet.admin;

import model.User;
import service.ProductService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "changeProductStatus", urlPatterns = {"/changeProductStatus"})
public class ChangeProductStatusServlet extends HttpServlet {

    private ProductService service = new ProductService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("idProduct"));
        boolean status = Integer.parseInt(request.getParameter("status")) != 1;
        try {
            service.changeStatus(id, status);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }

    }
}
