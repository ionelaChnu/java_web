import db.DataBaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/AddStudent.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String groupNumber = request.getParameter("group_number");
        int mathMark = Integer.parseInt(request.getParameter("math_mark"));
        int historyMark = Integer.valueOf(request.getParameter("history_mark"));
        int musicMark = Integer.valueOf(request.getParameter("music_mark"));

        List<String> errors = new ArrayList<>();
        if (mathMark < 2 || mathMark > 5) {
            errors.add("Math mark should be >=2 and <=5");
        }
        if (historyMark < 2 || historyMark > 5) {
            errors.add("History mark should be >=2 and <=5");
        }
        if (musicMark < 2 || musicMark > 5) {
            errors.add("Music mark should be >=2 and <=5");
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("groupNumber", groupNumber);
            request.setAttribute("mathMark", mathMark);
            request.setAttribute("historyMark", historyMark);
            request.setAttribute("musicMark", musicMark);
            getServletContext().getRequestDispatcher("/AddStudent.jsp").forward(request, response);
            return;
        } else {
            try {
                Connection connection = DataBaseConnection.getNewConnection();
                String query = "INSERT INTO student (first_name, last_name, group_number, math_mark, history_mark, music_mark) VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, firstName);
                preparedStmt.setString(2, lastName);
                preparedStmt.setString(3, groupNumber);
                preparedStmt.setString(4, String.valueOf(mathMark));
                preparedStmt.setString(5, String.valueOf(historyMark));
                preparedStmt.setString(6, String.valueOf(musicMark));
                preparedStmt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e);
            }
            request.setAttribute("message","domain.Student added successful");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        }
    }

}
