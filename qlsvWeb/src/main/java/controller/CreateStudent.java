package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import service.StudentService;
import service.StudentServiceIMPL;
@WebServlet(urlPatterns = "/create-student")
public class CreateStudent extends HttpServlet{

	StudentService sts = new StudentServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
//		
//		pw.println("<form action=\"/qlsvWeb/create-student\" method=\"post\">\r\n"
//				+ "	  <label>id:</label>\r\n"
//				+ "	  <br> <input type=\"number\" name=\"cID\" value = \"\">\r\n"
//				+ "	  <br> <label>name:</label>\r\n"
//				+ "	  <br> <input type=\"text\" name=\"cname\" value = \"\">\r\n"
//				+ "	  <br> <label>address:</label>\r\n"
//				+ "	  <br> <input type=\"text\" name=\"caddress\" value = \"\">\r\n"
//				+ "	  <br> <label>maSV:</label>\r\n"
//				+ "	  <br> <input type=\"text\" name=\"cmSV\" value = \"\">\r\n"
//				+ "	  <br> <button type = \"submit\">Create</button>\r\n"
//				+ "	</form>");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/student/CreateStudentForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("student/CreateStudentForm.jsp");

		String id = req.getParameter("cID");
		String name = req.getParameter("cname");
		String address = req.getParameter("caddress");
		String maSV = req.getParameter("cmSV");
		
		try {
			Student st = new Student();
			st.setId(Integer.parseInt(id));
			st.setName(name);
			st.setAddress(address);
			st.setMaSV(maSV);
			
			sts.insert(st);
//			pw.println("<p>Them thanh cong ! </p>");
			req.setAttribute("msg", "Them thanh cong");
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu");
//			pw.println("<p> Sai du lieu ! </p>");
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("msg", "Loi server");
//			pw.println("<p>Loi server !</p>");
			e.printStackTrace();
		}
		
		dispatcher.forward(req, resp);
	}
}
