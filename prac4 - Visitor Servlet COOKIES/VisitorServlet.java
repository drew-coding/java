package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VisitorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Cookie[] cookiesList = request.getCookies();

		int count = 1;
		if (null == cookiesList) {
			Cookie visitorCookie = new Cookie("counter", String.valueOf(count));
			response.addCookie(visitorCookie);
		} else {
			
			Cookie visitorCookie = null;
			for (int i = 0; i < cookiesList.length; i++) {
				if ("counter".equals(cookiesList[i].getName())) {
					visitorCookie = cookiesList[i];
					count = Integer.parseInt(visitorCookie.getValue());
					count++;
					visitorCookie.setValue(String.valueOf(count));
					response.addCookie(visitorCookie);
					break;
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.write("<h1>You have requested this servlet " + count + " times </h1>");
		out.close();
	}
}
