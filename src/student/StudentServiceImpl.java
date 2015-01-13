package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Student;
import student.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public List getStudentList() {
		List studentList = new ArrayList();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from student");
			
			while(rs.next()){
				Student student = new Student();
				
				student.setUserName(rs.getString("USER_NAME"));
				student.setPassword(rs.getString("PASSWORD"));
				student.setProvince(rs.getString("PROVINCE"));
				student.setGender(rs.getString("GENDER"));
				student.setHobbies(rs.getString("HOBBIES"));
				
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new RuntimeException("error when querying students ",e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				
				throw new RuntimeException("error when querying students ",e);
			}
		}

		return studentList;
	}

}
