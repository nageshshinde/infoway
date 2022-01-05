package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.app.model.Employee;

public class EmpployeeDAO {
	Connection con;
	PreparedStatement pstmt;

	public EmpployeeDAO() {
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
	public  String getconnection(Employee emp) {
		
		String status = "";
		try {
			pstmt=con.prepareStatement("insert into emp values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, emp.getEmpid());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getPassword());
			pstmt.setString(5, emp.getGender());
			pstmt.setString(6, emp.getDob());
			pstmt.setString(7, emp.getDept());
			int row=pstmt.executeUpdate();
			if (row >= 1) {
				status="success";
			}else {
				status="fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public Employee login(Employee emp) {
		// TODO Auto-generated method stub
		Employee em = null;
		try {
			pstmt=con.prepareStatement("select * from emp where email=? and password=?");
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPassword());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				em=new Employee();
				System.out.println(rs.getString(2));
				em.setEmail(rs.getString(3));
				em.setEmpName(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return em;
	}
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		List<Employee> list=new ArrayList<>();
		try {
			pstmt=con.prepareStatement("select * from emp");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs=stmt.executeQuery("select * from emp");
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setEmpid(rs.getInt(1));
				emp.setEmpName(rs.getString("name"));
				emp.setEmail(rs.getString("email"));
				emp.setDept(rs.getString("dept"));
				emp.setDob(rs.getString("dob"));
				list.add(emp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	

}
