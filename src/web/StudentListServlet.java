package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Student;
import student.StudentServiceImpl;
import student.StudentService;


public class StudentListServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			StudentService studentService = new StudentServiceImpl();
			
//			StudentService studentService = ServiceFactory.getStudentService();
			
			List studentList = studentService.getStudentList();

			toStudentList(resp, studentList);
		} catch (Exception e){
			toError(resp,e.getMessage());
		}
		
	}

	private void toError(HttpServletResponse resp, String message) throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>Error</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h2 align=\"center\">Error</h2>");
		out.println("	<hr>");
		out.println("	System Error," + message + "!");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	private void toStudentList(HttpServletResponse resp, List studentList)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("	<title>Student List</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h2 align=\"center\">Student List</h2>");
		out.println("	<hr>");
		out.println("	<center>");
		out.println("		<table border=\"1\">");
		out.println("			<tr>");
		out.println("				<th>USER_NAME</th>");
		out.println("				<th>PASSWORD</th>");
		out.println("				<th>PROVINCE</th>");
		out.println("				<th>GENDER</th>");
		out.println("				<th>HOBBIES</th>");
		out.println("			</tr>");

		for(Iterator<Student> it = studentList.iterator();it.hasNext();){
			Student student = it.next();
			
			out.println("			<tr>");
			out.println("				<td>" + student.getUserName() + "</td>");
			out.println("				<td>" + student.getPassword() + "</td>");
			out.println("				<td>" + student.getProvince() + "</td>");
			out.println("				<td>" + student.getGender() + "</td>");
			out.println("				<td>" + student.getHobbies() + "</td>");
			out.println("			</tr>");
		}
		
		out.println("		</table>");
		out.println("	</center>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	

}

