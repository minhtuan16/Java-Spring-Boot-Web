package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import service.CourseService;
import service.CourseServiceIMPL;
@WebServlet(urlPatterns = "/update-course")

public class UpdateCourse extends HttpServlet{
	CourseService cs = new CourseServiceIMPL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/course/UpdateCourseForm.jsp");
		dispatcher.forward(req, resp);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/course/UpdateCourseForm.jsp");
		
		String id = req.getParameter("ID");
		String name = req.getParameter("name");
		
		try {
			Course c = new Course();
			c.setId(Integer.parseInt(id));
			c.setName(name);
			
			cs.update(c);
			
			req.setAttribute("", "Sua thanh cong !");
		} catch (NumberFormatException e) {
			
			req.setAttribute("", "Sai du lieu !");
			
			e.printStackTrace();
		} catch(SQLException e) {
			
			req.setAttribute("msg", "Loi server !");
			
			e.printStackTrace();
		}
		
		dispatcher.forward(req, resp);
	}
}
