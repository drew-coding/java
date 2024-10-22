package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String uemail = request.getParameter("uemail");
		String ucountry = request.getParameter("ucountry");
	
		boolean result = insertIntoDatabase(uname, upass, uemail, ucountry);
		
		PrintWriter out = response.getWriter();
		if (result) {
			out.write("<h1> Record saved to database successfully </h1>");
		} else {
			out.write("<h1> Failed to save record to database</h1>");
		}
		out.close();
	}
	
	private boolean insertIntoDatabase(String uname, String upass, String uemail,String ucountry) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection dbConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/USERS_DB", "root", "root");
			
			Statement query = dbConn.createStatement();
			query.executeUpdate("INSERT INTO REGISTRATION (USERNAME, PASSWORD, EMAIL, COUNTRY)"
					+ " VALUES ('" + uname + "','" + upass + "','" + uemail + "','" + ucountry + "')");

			query.close();
			dbConn.close();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
}
