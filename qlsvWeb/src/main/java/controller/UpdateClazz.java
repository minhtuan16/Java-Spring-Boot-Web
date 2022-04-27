package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClazzDao;
import dao.ClazzDaoIMPL;
import model.Clazz;
import model.Course;
import service.ClazzService;
import service.ClazzServiceIMPL;
import service.CourseService;
import service.CourseServiceIMPL;

@WebServlet(urlPatterns = "/update-clazz")
public class UpdateClazz extends HttpServlet {
	ClazzService cls = new ClazzServiceIMPL();
	CourseService cs = new CourseServiceIMPL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/clazz/UpdateClazzForm.jsp");

		try {
			List<Course> courseList = cs.getAll();
			req.setAttribute("courseList", courseList);
		} catch (SQLException e) {
			req.setAttribute("msg", "Xay ra loi !");
			e.printStackTrace();
		}

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/clazz/UpdateClazzForm.jsp");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String course_id = req.getParameter("course_id");

		try {
			Clazz cl = new Clazz();

			cl.setId(Integer.parseInt(id));
			cl.setName(name);

			Course c = new Course();
			c.setId(Integer.parseInt(course_id));

			cl.setCourse(c);

			cls.update(cl);

			req.setAttribute("msg", "Sua thanh cong !");
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Sai du lieu");

			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("msg", "Loi server !");
			e.printStackTrace();
		}

		try {
			List<Course> courseList = cs.getAll();

			req.setAttribute("courseList", courseList);
		} catch (SQLException e) {
			req.setAttribute("msg", "Xay ra loi !");

			e.printStackTrace();
		}

		dispatcher.forward(req, resp);
	}
}
