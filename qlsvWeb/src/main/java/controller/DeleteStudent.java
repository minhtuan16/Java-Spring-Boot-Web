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
@WebServlet(urlPatterns = "/delete-student")

public class DeleteStudent extends HttpServlet{

	StudentService sts = new StudentServiceIMPL();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		
//		resp.setContentType("text/html");
//		
//		PrintWriter pw = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/student/DeleteStudentView.jsp");
		
		try {
			sts.delete(Integer.parseInt(idStr)); // ep kieu id tu String -> Int
			
//			pw.println("<p>Xoa thanh cong id: " + idStr + " ! </p>");
			
			req.setAttribute("msg", "Xoa thanh cong id: " + idStr + "!</p>");
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu id !");
			
//			pw.println("<p>Sai du lieu id ! </p>");
			
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("msg", "Loi server");
			
//			pw.println("<p>Loi server ! </p>");
			
			e.printStackTrace();
		}
		
//		pw.println("<a href = '/qlsvWeb/menu-student'>Quay lai trang chu </p>");
		
		dispatcher.forward(req, resp);
	}
}
