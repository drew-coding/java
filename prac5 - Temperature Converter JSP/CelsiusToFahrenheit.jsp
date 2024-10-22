<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Temperature converter page</title>
</head>
<body>
	<%!
		public double getFahrenheit(double celsius) {
			return (celsius * 1.8d) + 32;
		}
	%>
	
	<%
		String input = request.getParameter("celtemp");
		double celsius = Double.parseDouble(input);
	%>
	
	<h1>Temperature <%= celsius %> in degree celsius is equivalent <%= getFahrenheit(celsius) %> fahrenheit</h1>
</body>
</html>