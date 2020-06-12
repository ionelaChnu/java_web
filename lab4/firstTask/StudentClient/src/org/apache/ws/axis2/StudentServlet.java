package org.apache.ws.axis2;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ws.axis2.StudentWSStub.GetAllStudents;
import org.apache.ws.axis2.StudentWSStub.GetStudentNamesWithOnlyHightGrades;


public class StudentServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public StudentServlet() {
		super();
    }

    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	StudentWSStub stub = new StudentWSStub("http://localhost:8080/StudentService/services/StudentWS?wsdl");
    	GetAllStudents gs = new GetAllStudents();
    	String[] allStudents = stub.getAllStudents(gs).get_return();
    	
    	GetStudentNamesWithOnlyHightGrades ghs = new GetStudentNamesWithOnlyHightGrades();
    	String[] goodStudents = stub.getStudentNamesWithOnlyHightGrades(ghs).get_return();
    	
    	request.setAttribute("percent",goodStudents.length/allStudents.length);
    	request.setAttribute("students",Arrays.asList(goodStudents));
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    	dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
