package servlet.customer;

import model.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "userProducts", urlPatterns = {"/userProducts"})
public class UserProductsServlet extends HttpServlet {

    private ProductService service = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> activeProducts = service.getAllActiveProducts();

            request.setAttribute("products", activeProducts);

        } catch (SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        getServletContext().getRequestDispatcher("/views/customer/UserProducts.jsp").forward(request, response);
    }
}
