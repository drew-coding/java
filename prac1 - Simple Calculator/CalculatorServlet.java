package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String firstnum = request.getParameter("firstnum");
		int num1 = Integer.parseInt(firstnum);
		
		String secondnum = request.getParameter("secondnum");
		int num2 = Integer.parseInt(secondnum);
	
		String operator = request.getParameter("operator");
		
		float result = 0;
		PrintWriter out = response.getWriter();
		switch(operator) {
		
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;	
			case "*":
				result = num1 * num2;
				break;
			case "/":
				result = num1 / num2;
				break;
			default:
				out.write("Incorrect operator, allowed values are +,-,*,/");
				break;
		}
		
		out.write("<h1> Final result " + result + "</h1>");
		out.close();
	}
}
