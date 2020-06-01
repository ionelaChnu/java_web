package servlet.admin;

import model.Product;
import service.ProductService;
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

@WebServlet(name = "product", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private ProductService service = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter("id");
        if (parameterId != null) {
            UUID id = UUID.fromString(parameterId);
            try {
                Product product = service.getProductById(id);
                request.setAttribute("id", product.getId());
                request.setAttribute("name", product.getName());
                request.setAttribute("description", product.getDescription());
                request.setAttribute("price", product.getPrice());
                request.setAttribute("mode", "Update");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("mode", "Add");
        }
        getServletContext().getRequestDispatcher("/views/admin/NewProduct.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterId = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.valueOf(request.getParameter("price"));
        List<String> errors = new ArrayList<>();
        try {
            if (parameterId != null && !parameterId.isEmpty()) {
                UUID id = UUID.fromString(parameterId);
                Product product = service.getProductById(id);
                service.updateProduct(product, name, description, price);
            } else {
                errors = service.saveProduct(name, description, price);
            }
            if (!errors.isEmpty()) {
                request.setAttribute("registerError", errors);
                request.setAttribute("name", name);
                request.setAttribute("description", description);
                request.setAttribute("price", price);
                getServletContext().getRequestDispatcher("/views/admin/NewProduct.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/products"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        UUID id = UUID.fromString(s);
        try {
            service.delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    }
}
