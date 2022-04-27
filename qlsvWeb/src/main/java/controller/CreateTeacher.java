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

import model.Teacher;
import service.TeacherService;
import service.TeacherServiceIMPL;

@WebServlet(urlPatterns = "/create-teacher")
public class CreateTeacher extends HttpServlet {

	TeacherService tes = new TeacherServiceIMPL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher/createTeacher.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("teacher/createTeacher.jsp");

		String id = req.getParameter("cID");
		String name = req.getParameter("cname");
		String address = req.getParameter("caddress");
		String email = req.getParameter("cemail");

		try {
			Teacher t = new Teacher();
			t.setId(Integer.parseInt(id));
			t.setName(name);
			t.setAddress(address);
			t.setEmail(email);

			tes.insert(t);

			req.setAttribute("msg", "Them thanh cong !");
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
