package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TeacherService;
import service.TeacherServiceIMPL;
@WebServlet(urlPatterns = "/delete-teacher")

public class DeleteTeacher extends HttpServlet{

	TeacherService tes = new TeacherServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher/deleteTeacher.jsp");
		
		try {
			tes.delete(Integer.parseInt(idStr)); // ep kieu id tu String -> Int
			
			req.setAttribute("msg", "Xoa thanh cong id: " + idStr + " !");
		} catch (NumberFormatException e) {
			
			req.setAttribute("msg", "Sai du lieu !");
						
			e.printStackTrace();
		} catch (SQLException e) {
			
			req.setAttribute("msg", "Loi server !");
			
			e.printStackTrace();
		}
		dispatcher.forward(req, resp);
		return;
	}
}
