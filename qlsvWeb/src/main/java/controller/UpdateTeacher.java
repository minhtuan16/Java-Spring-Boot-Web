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
import model.Teacher;
import service.StudentService;
import service.StudentServiceIMPL;
import service.TeacherService;
import service.TeacherServiceIMPL;
@WebServlet(urlPatterns = "/update-teacher")
public class UpdateTeacher extends HttpServlet{

	TeacherService tes = new TeacherServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher/UpdateTeacher.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher/UpdateTeacher.jsp");
		
		String id = req.getParameter("ID");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		
		try {
			Teacher t = new Teacher();
			t.setId(Integer.parseInt(id));
			t.setName(name);
			t.setAddress(address);
			t.setEmail(email);
			
			tes.update(t);
			
			req.setAttribute("msg", "Sua thanh cong !");
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu !");
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("msg", "Loi server !");
			e.printStackTrace();
		}
		
//		pw.println("<a href = '/qlsvWeb/menu-teacher'>Quay lai trang chu </p>");
//		pw.println("<a href = '/qlsvWeb/update-teacher'>Tiep tuc sua </p>");
		
		dispatcher.forward(req, resp);
	}
}
