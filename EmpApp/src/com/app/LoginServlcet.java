package com.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.EmpployeeDAO;
import com.app.model.Employee;

/**
 * Servlet implementation class LoginServlcet
 */
@WebServlet("/login")
public class LoginServlcet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlcet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//set MIME Type
		response.setContentType("text/html");
		String email=request.getParameter("emailId");
		String password=request.getParameter("pass");
		Employee emp=new Employee();
		emp.setEmail(email);
		emp.setPassword(password);
		EmpployeeDAO empDao=new EmpployeeDAO();
		Employee status=empDao.login(emp);
	
		try {
		if(status!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("name",status.getEmpName());
			request.getRequestDispatcher("/profile").forward(request, response);
		}
		else {
			response.getWriter().print("<h1>Sorry Invalid UserName and Password");
			request.getRequestDispatcher("login.html").include(request ,response);
		}
		}catch (Exception e) {
			// TODO: handle exception
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
