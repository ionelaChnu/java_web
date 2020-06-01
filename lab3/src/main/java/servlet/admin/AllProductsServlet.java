package servlet.admin;

import model.Product;
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
import java.util.List;

@WebServlet(name = "products", urlPatterns = {"/products"})
public class AllProductsServlet extends HttpServlet {

    private ProductService service = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> activeProducts = service.getAllActiveProducts();
            List<Product> disabledProducts = service.getAllDisabledProducts();

            request.setAttribute("activeProducts", activeProducts);
            request.setAttribute("disabledProducts", disabledProducts);

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        getServletContext().getRequestDispatcher("/views/admin/AllProducts.jsp").forward(request, response);
    }
}
