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
import service.ClazzService;
import service.ClazzServiceIMPL;

@WebServlet(urlPatterns = "/menu-clazz")
public class ClazzController extends HttpServlet {

	ClazzService cS = new ClazzServiceIMPL();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/clazz/ClazzControllerView.jsp");

		try {
			List<Clazz> cList = cS.selectAll();
			req.setAttribute("cList", cList);
		} catch (SQLException e) {
			req.setAttribute("msg", "Xay ra loi !");

			e.printStackTrace();
		}

		dispatcher.forward(req, resp);

	}

}
