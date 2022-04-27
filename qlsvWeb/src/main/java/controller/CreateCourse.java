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

import model.Course;
import service.CourseService;
import service.CourseServiceIMPL;
@WebServlet(urlPatterns = "/create-course")

public class CreateCourse extends HttpServlet{
	CourseService cs = new CourseServiceIMPL();

	//Mô hình MVC: Model - view - Controller
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/course/CreateCourseForm.jsp"); // dung de dan duong link
		
		dispatcher.forward(req, resp); // chuyen sang duong link de xu ly 
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/course/CreateCourseForm.jsp"); // dung de dan duong link
			
		
		String id = req.getParameter("cID");
		String name = req.getParameter("cname");
		
		try {
			Course c = new Course();
			c.setId(Integer.parseInt(id));
			c.setName(name);
			
			cs.insert(c);
			
			req.setAttribute("msg", "Them thanh cong !"); //setAttribute la de them gia tri req
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu !");
			e.printStackTrace();
		} catch(SQLException e) {
			req.setAttribute("msg", "Loi server !");
			e.printStackTrace();
		}
		
		dispatcher.forward(req, resp); // chuyen sang duong link de xu ly 
	}
}
