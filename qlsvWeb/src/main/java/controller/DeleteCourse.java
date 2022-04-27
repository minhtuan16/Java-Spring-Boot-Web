package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseService;
import service.CourseServiceIMPL;
@WebServlet(urlPatterns = "/delete-course")

public class DeleteCourse extends HttpServlet{
	CourseService cs = new CourseServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// id = ????
		String idStr = req.getParameter("id"); // id tra ve ???
		
		resp.setContentType("text/html");
		
		PrintWriter pw = resp.getWriter();
		try {
			cs.delete(Integer.parseInt(idStr)); // ep tu String --> Int
			
			pw.println("<p>Xoa thanh cong course-id: " + idStr + " ! </p>");
		} catch (NumberFormatException e) {
			
			pw.println("<p>Sai du lieu id ! </p>");
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			pw.println("<p>Loi server ! </p>");
			
			e.printStackTrace();
		} 
		
		pw.println("<a href = '/qlsvWeb/menu-course'>Quay lai</p>");
	}
}
