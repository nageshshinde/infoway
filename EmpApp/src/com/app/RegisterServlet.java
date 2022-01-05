package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.EmpployeeDAO;
import com.app.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/emp")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("eName");
		String email=request.getParameter("emailId");
		String pass=request.getParameter("pass");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String dept=request.getParameter("dept");
		Employee emp=new Employee();
		emp.setEmpName(name);
		emp.setDept(dept);
		emp.setDob(dob);
		emp.setEmail(email);
		emp.setGender(gender);
		emp.setPassword(pass);
		EmpployeeDAO empdao=new EmpployeeDAO();
		String status=empdao.getconnection(emp);
		if(status.equals("success")) {
			response.sendRedirect("profile");
		}
		else {
			response.sendRedirect("error.html");
		}
				
	}

}
