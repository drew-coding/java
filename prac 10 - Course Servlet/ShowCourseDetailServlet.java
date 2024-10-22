package tyit.ej;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCourseDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String course = request.getParameter("coursesel");
		String forwardPg = course + ".html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPg);
		dispatcher.forward(request, response);
	}
}
