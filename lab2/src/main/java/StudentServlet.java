import db.DataBaseConnection;
import domain.ResultDTO;
import domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            String subject = request.getParameter("subject");

            ResultDTO result = new ResultDTO().setSubject(subject);
            Connection connection = DataBaseConnection.getNewConnection();
            getMarkCounts(result, subject, connection);
            getMarkAverage(result, subject, connection);
            getStudents(result, subject, connection);
            connection.close();

            request.setAttribute("result", result);
            request.setAttribute("subject", subject);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(request, response);
    }

    private void getMarkCounts(ResultDTO resultDTO, String subject, Connection connection) throws SQLException {
        String query = "SELECT " + subject + "_mark, count(id) FROM student GROUP BY " + subject + "_mark";
        ResultSet resultSet = connection.createStatement().executeQuery(query);

        while (resultSet.next()) {
            resultDTO.getMarkCount().put(resultSet.getInt(1), resultSet.getInt(2));
        }
        resultSet.close();
    }

    private void getMarkAverage(ResultDTO resultDTO, String subject, Connection connection) throws SQLException {
        String query = "SELECT avg(" + subject + "_mark) FROM student WHERE 1=1";
        ResultSet resultSet = connection.createStatement().executeQuery(query);

        while (resultSet.next()) {
            resultDTO.setAverage(resultSet.getDouble(1));
        }
        resultSet.close();
    }

    private void getStudents(ResultDTO resultDTO, String subject, Connection connection) throws SQLException {
        String query = "SELECT * FROM student ORDER BY " + subject + "_mark DESC";
        ResultSet resultSet = connection.createStatement().executeQuery(query);

        while (resultSet.next()) {
            resultDTO.getStudents().add(
                    new Student()
                            .setFirstName(resultSet.getString(1))
                            .setId(resultSet.getLong(2))
                            .setLastName(resultSet.getString(3))
                            .setGroupNumber(resultSet.getString(4))
                            .setMathMark(resultSet.getInt(5))
                            .setHistoryMark(resultSet.getInt(6))
                            .setMusicMark(resultSet.getInt(7))
            );
        }
        resultSet.close();
    }

}
