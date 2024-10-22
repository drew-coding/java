package tyit.ej;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CreateBookServlet extends HttpServlet {

	private SessionFactory factory;
	
	public void init() throws ServletException {
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String name = request.getParameter("bname");
		String author = request.getParameter("bauthor");
		String subject = request.getParameter("bsubject");
		String bprice = request.getParameter("bprice");
		float price = Float.parseFloat(bprice);
		
		BooksCatalogue book = new BooksCatalogue();
		book.setName(name);
		book.setAuthor(author);
		book.setSubject(subject);
		book.setPrice(price);
		
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		session.save(book);
		txn.commit();
		
		session.close();
		
		PrintWriter out = response.getWriter();
		out.write("<h1>Record saved successfully</h1>");
		out.close();
	}

	public void destroy() {
		factory.close();
	}
}
