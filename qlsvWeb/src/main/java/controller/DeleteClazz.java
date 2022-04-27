package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import service.ClazzService;
import service.ClazzServiceIMPL;
import service.CourseService;
import service.CourseServiceIMPL;
@WebServlet(urlPatterns = "/delete-clazz")
public class DeleteClazz extends HttpServlet{

	ClazzService cls = new ClazzServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/clazz/DeleteClazz.jsp");
		String id = req.getParameter("id");
		
		try {
			cls.delete(Integer.parseInt(id));
			
			req.setAttribute("msg", "Xoa thanh cong id: " + id + "!</p>");
		} catch(NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu MySQL !");
			
			e.printStackTrace();
		} catch(SQLException e) {
			req.setAttribute("msg", "Loi server !");
			
			e.printStackTrace();
		}
		
		dispatcher.forward(req, resp);
	}
}
