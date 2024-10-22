package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ShowBooksServlet extends HttpServlet {

	private SessionFactory factory;
	
	public void init() throws ServletException {
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Session session = factory.openSession();
		Query query = session.createQuery("from tyit.ej.BooksCatalogue WHERE subject='java'");
		List records = query.list();
		PrintWriter out = response.getWriter();
		out.write("<table border='1'>");
		out.write("<tr>");
		out.write("<th>ID</th>");
		out.write("<th>NAME</th>");
		out.write("<th>AUTHOR</th>");
		out.write("<th>SUBJECT</th>");
		out.write("<th>PRICE</th>");
		out.write("</tr>");
		for (int i = 0; i < records.size(); i++) {
			BooksCatalogue book = (BooksCatalogue) records.get(i);
			out.write("<tr>");
			out.write("<td>" + book.getId() + "</td>");
			out.write("<td>" + book.getName() + "</td>");
			out.write("<td>" + book.getAuthor() + "</td>");
			out.write("<td>" + book.getSubject() + "</td>");
			out.write("<td>" + book.getPrice() + "</td>");
			out.write("</tr>");
		}	
		out.write("</table>");
		out.close();
		session.close();
	}
	
	public void destroy() {
		factory.close();
	}
}
