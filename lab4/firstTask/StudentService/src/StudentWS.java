import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentWS {

	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection("jdbc:mysql://localhost:3306//mydb;create=true;user=root;password=root");
	}
	
	public List<String> getAllStudents(){
		System.out.println();
		try {
			List<String> students = new ArrayList<String>();
			Connection con = getConnection();
			ResultSet rs = con.createStatement().executeQuery("Select name,surname from students");
			while(rs.next()) {
				students.add("Student name: "+rs.getString(1)+" surname "+rs.getString(2));
			}
			rs.close();
			con.close();
			return students;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public List<String> getStudentNamesWithOnlyHightGrades(){
		System.out.println();
		try {
			List<String> students = new ArrayList<String>();
			Connection con = getConnection();
			ResultSet rs = con.createStatement().executeQuery("Select name,surname,age"
					+ "from students where mark1>3 and mark2>3 and mark3>3");
			while(rs.next()) {
				students.add("Student name: "+rs.getString(1)+" surname "+rs.getString(2)+" age"+ rs.getInt(3));
			}
			rs.close();
			con.close();
			return students;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
}
