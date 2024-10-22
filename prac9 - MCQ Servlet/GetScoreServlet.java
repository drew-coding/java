package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetScoreServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int q1Marks = Integer.parseInt(request.getParameter("q1"));
		int q2Marks = Integer.parseInt(request.getParameter("q2"));
		int q3Marks = Integer.parseInt(request.getParameter("q3"));
		
		int totalScore = q1Marks + q2Marks + q3Marks;
		
		PrintWriter out = response.getWriter();
		out.write("<h1>Total score " + totalScore + "/30</h1>");
		out.close();
	}
}
