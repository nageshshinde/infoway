package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		request.getRequestDispatcher("link.html").include(request, response);
		if(session!=null) {
			String name=(String)session.getAttribute("name");
			out.print("<h2 align='right'>Welcome "+name);
			out.print("</h2><center>_________________________________________<br>");
			out.print("<p>Java Berhad, an investment holding company, "
					+ "engages in the harvesting and trading of raw timber products in Malaysia. "
					+ "The company is also involved in manufacturing and trading downstream timber "
					+ "products; oil palm plantation and property development activities; "
					+ "and manufacturing and marketing veneer related products. <br>"
					+ "In addition, it provides transportation, engineering, and management services, "
					+ "as well as operates as a timber contractor.<br>"
					+ " Java Berhad is based in Kuala Lumpur, Malaysia.</p>");
			out.print("<br> Bye..."+name);
		}
		else {
			out.print("<h2 align='center'>Please login first then Visit</h2>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
