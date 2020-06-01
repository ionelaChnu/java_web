package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {
    private FilterConfig filterConfig=null;

    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig=filterConfig;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        response.setContentType("text/html; charset=UTF-8");
    }
    @Override
    public void destroy() {
        this.filterConfig=null;
    }

}

