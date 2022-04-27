package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import model.Student;
import service.StudentService;
import service.StudentServiceIMPL;
@WebServlet(urlPatterns = "/menu-student")
public class StudentController extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}

	StudentService sts = new StudentServiceIMPL();
	Person p = new Person();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
//		
//		pw.println("<h1>Menu Student</h1>");
//		pw.println("<a href = '/qlsvWeb/create-student'>Create Student</a>");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/student/StudentControllerView.jsp");
		
		try {

			List<Student> sList = sts.read();
			
			req.setAttribute("sList", sList);
			
//			for(Student c : sList) {
//				pw.println("<p>------------------</p>");
//				pw.println("<p>ID: " + c.getId() + "</p>");
//				pw.println("<p>Name: " + c.getName() + "</p>");
//				pw.println("<p>Address: " + c.getAddress() + "</p>");
//				pw.println("<p>Ma SV: " + c.getMaSV() + "</p>");
//				
//				pw.println("<a href = '/qlsvWeb/update-student?ID = " + c.getId() +" &Name = " + c.getName() + " &Address = " + c.getAddress() + " &Ma SV = " + c.getMaSV() + "'>Update Student</a>");
//				pw.println("<a href = '/qlsvWeb/delete-student?id= " + c.getId() + "'>Delete Student</a>");
//			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "Xay ra loi!");
//			pw.println("<p>Xay ra loi ! </p>");
		}
		dispatcher.forward(req, resp);
	}
}
