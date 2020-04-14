package filter;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/users", "/products", "/product"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        User user = (User) session.getAttribute("currUser");

        if (user != null && user.getAuthority().toString().equals(String.valueOf("ADMIN"))) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(((HttpServletResponse) response).encodeRedirectURL(((HttpServletRequest) request).getContextPath() + "/accessDenied"));
        }
    }
    @Override
    public void destroy() {

    }
}