
//This is Comment.
package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.EmpployeeDAO;
import com.app.model.Employee;

/**
 * Servlet implementation class ListOfEmployee
 */
@WebServlet("/list")
public class ListOfEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con;
    public ListOfEmployee() {
        super();
        // TODO Auto-generated constructor stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		int pageNumber=Integer.parseInt(request.getParameter("no"));
		try {
		
		HttpSession session=request.getSession(false);
		request.getRequestDispatcher("link.html").include(request, response);
		if(session!=null) {
		pw.print("<html><body><center><h1>List of Employees</h1>");
		pw.print("<table border='2'><tr><th>Emp Id</th><th>Emp Name</th><th>Emp email</th><th>");
		pw.print("Date of Birth</th><th>dept</th><th>Action</th></tr>");
		Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=stmt.executeQuery("select * from emp");
		int start=pageNumber*3-3+1;
		rs.absolute(start);
		int i=1;
		do {
			pw.print("<tr><td>"+rs.getInt("empid")+"</td>");
			int id=rs.getInt("empid");
			System.out.println("id>>>>"+id);
			pw.print("<td>"+rs.getString("name")+"</td>");
			pw.print("<td>"+rs.getString("email")+"</td>");
			pw.print("<td>"+rs.getString("dept")+"</td>");
			pw.print("<td>"+rs.getString("dob")+"</td>");
			pw.print("<td><a href='update.html'>update</a>&nbsp &nbsp" + 
					"<a href=delete?id="+id+">delete</a>&nbsp &nbsp<a href='view.html'>view</a></td></tr>");
		i++;	
		}while(rs.next()&&i!=4);
		pw.print("</table>");
		rs.last();
		int total=rs.getRow();
		int page=total/3;
		if(total%3==0) {
			page++;
		}
		if(pageNumber==page) {
			int n=pageNumber+1;
			pw.print("&nbsp&nbsp<a href=list?no="+n+">Next</a>");
		}
		else if(page==1) {
			int n=page-1;
			pw.print("&nbsp&nbsp<a href=list?no="+n+">Prev</a>");
		}else {
			int n=page-1;
			pw.print("&nbsp&nbsp<a href=list?no="+n+">Prev</a>");
			int n1=pageNumber+1;
			pw.print("&nbsp&nbsp<a href=list?no="+n1+">Next</a>");
		}
		
		}else {
			pw.print("<h2 align='center'>Please login first then Visit</h2>");
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("error.html");
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
