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
@WebServlet(urlPatterns = "/update-student")
public class UpdateStudent extends HttpServlet{

	StudentService sts = new StudentServiceIMPL();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/student/UpdateStudent.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/student/UpdateStudent.jsp");
		
		String id = req.getParameter("ID");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String maSV = req.getParameter("mSV");
		
		try {
			Student st = new Student();
			st.setId(Integer.parseInt(id));
			st.setName(name);
			st.setAddress(address);
			st.setMaSV(maSV);
			
			sts.update(st);
			
			req.setAttribute("msg", "Sua thanh cong !");
			
		} catch (NumberFormatException e) {
			
			req.setAttribute("msg", "Sai du lieu !");
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			req.setAttribute("msg", "Loi server !");
			
			e.printStackTrace();
		}
		
//		pw.println("<a href = '/qlsvWeb/menu-student'>Quay lai trang chu </p>");
		
		dispatcher.forward(req, resp);
	}
}
