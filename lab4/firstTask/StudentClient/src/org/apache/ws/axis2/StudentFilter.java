package org.apache.ws.axis2;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.ws.axis2.StudentWSStub.GetAllStudents;

/**
 * Servlet Filter implementation class PointsFilter
 */
public class StudentFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StudentFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		StudentWSStub stub = new StudentWSStub("http://localhost:8080/StudentService/services/StudentWS?wsdl");
    	GetAllStudents gs = new GetAllStudents();
    	String[] allStudents = stub.getAllStudents(gs).get_return();
		for(Object st:allStudents) {
			System.out.println(st);
		}
		request.setAttribute("points",Arrays.asList(allStudents));
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

