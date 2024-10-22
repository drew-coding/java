package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		
		boolean result = authentication(uname, upass);
		if (result) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.html");
			dispatcher.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.write("<h1>Incorrect username password combination</h1>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
			dispatcher.include(request, response);
			out.close();
		}
	}
	
	
	private boolean authentication(String uname, String upass) {
		
		boolean isValid = false;
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection dbConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/USERS_DB", "root", "root");
			
			PreparedStatement query = dbConn.prepareStatement(
					"SELECT COUNT(*) FROM REGISTRATION WHERE USERNAME=? AND PASSWORD=?");
			query.setString(1, uname);
			query.setString(2, upass);
			
			
			ResultSet result = query.executeQuery();
			while (result.next()) {
				int count = result.getInt(1);
				if (count == 1) {
					isValid = true;
					break;
				}
			}
			
			query.close();
			dbConn.close();
			
		} catch (Exception ex) {
			isValid = false;
		}
		return isValid;
	}
}
