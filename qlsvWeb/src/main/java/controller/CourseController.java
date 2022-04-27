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

import model.Course;
import service.CourseService;
import service.CourseServiceIMPL;

@WebServlet(urlPatterns = "/menu-course")

public class CourseController extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}
	
	
	//methods: get, post, put, delete
	
	CourseService cs = new CourseServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/course/CourseControllerView.jsp");
		
		try {
			List<Course> courselist = cs.getAll();
			
			req.setAttribute("courselist", courselist);
//			//java 8
//			courselist.forEach(c -> {
//				pw.println("<p>-----------------</p>");
//				pw.println("<p>Id: " + c.getId() + "</p>");
//				pw.println("<p>Name: " + c.getName() + "</p>");
//				
//				pw.println("<a href = '/qlsvWeb/delete-course?id=" + c.getId() + "'>Delete Course</a>");
//				pw.println("<a href = '/qlsvWeb/update-course?id=" + c.getId() + " &name=" + c.getName() + "'>Update Course</a>");
//				
//			});
		} catch(SQLException e) {
			e.printStackTrace();
			
			req.setAttribute("msg", "Xay ra loi !");
		}
		
		dispatcher.forward(req, resp);
	}
}
