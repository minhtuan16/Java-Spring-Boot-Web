package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Clazz;
import model.Course;
import service.ClazzService;
import service.ClazzServiceIMPL;
import service.CourseService;
import service.CourseServiceIMPL;

@WebServlet(urlPatterns = "/create-clazz")
public class CreateClazz extends HttpServlet {

	ClazzService cS = new ClazzServiceIMPL();
	CourseService courseS = new CourseServiceIMPL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/clazz/CreateClazzForm.jsp");
		try {
			List<Course> courseList = courseS.getAll();
			req.setAttribute("courseList", courseList);
		} catch (SQLException e) {
			req.setAttribute("msg", "Xay ra loi !");

			e.printStackTrace();
		}
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("clazz/CreateClazzForm.jsp");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String course_id = req.getParameter("course_id");

		try {
			Clazz cl = new Clazz();

			cl.setId(Integer.parseInt(id));
			cl.setName(name);

			Course c = new Course();
			c.setId(Integer.parseInt(course_id));
//			c.setName(name);

			cl.setCourse(c);

			cS.insert(cl);

			req.setAttribute("msg", "Them thanh cong !");

		} catch (NumberFormatException e) {

			req.setAttribute("msg", "Sai du lieu !");

			e.printStackTrace();
		} catch (SQLException e) {

			req.setAttribute("msg", "Loi server !");

			e.printStackTrace();
		}

		try {
			List<Course> courseList = courseS.getAll();
			req.setAttribute("courseList", courseList);
		} catch (SQLException e) {
			req.setAttribute("msg", "Xay ra loi !");

			e.printStackTrace();
		}

		dispatcher.forward(req, resp);
	}
}
