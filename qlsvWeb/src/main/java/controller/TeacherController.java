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

import model.Teacher;
import service.TeacherService;
import service.TeacherServiceIMPL;
@WebServlet(urlPatterns = "/menu-teacher")
public class TeacherController extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}

	TeacherService tes = new TeacherServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
//		
//		pw.println("<h1>Menu Teacher</h1>");
//		pw.println("<a href = '/qlsvWeb/create-teacher'>Create Teacher</a>");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher/TeacherControllerView.jsp");
		
		try {

			List<Teacher> tList = tes.read();
			
			req.setAttribute("tList", tList);

//			tList.forEach(t -> {
//				pw.println("<p>------------------</p>");
//				pw.println("<p>ID: " + t.getId() + "</p>");
//				pw.println("<p>Name: " + t.getName() + "</p>");
//				pw.println("<p>Address: " + t.getAddress() + "</p>");
//				pw.println("<p>Email: " + t.getEmail() + "</p>");
//				
//				pw.println("<a href = '/qlsvWeb/update-teacher'>Update Teacher</a>");
//				pw.println("<a href = '/qlsvWeb/delete-teacher'>Delete Teacher</a>");
//			});
		} catch (SQLException e) {
			e.printStackTrace();
//			pw.println("<p>Xay ra loi ! </p>");
			
			req.setAttribute("msg", "Xay ra loi !");	
		}
		
		dispatcher.forward(req, resp);
	}
}
