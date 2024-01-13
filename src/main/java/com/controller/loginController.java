package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JdbcConnection;
import com.dao.LoginDAO;
import com.model.Reservation;

@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private LoginDAO loginDao;
    public void init() throws ServletException {
    	loginDao = new LoginDAO();
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        boolean LoginResult= false;
        String username = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber");
        System.out.println(username+phoneNumber);
        try {			
	        Connection con = JdbcConnection.Connect();
	        con.setAutoCommit(false);
	        LoginResult = loginDao.validateLogin(username,phoneNumber);
	        if(LoginResult) {
	        	List<Reservation> CustomerDataList= loginDao.ShowCustomerData
	        			(username);
	        	request.setAttribute("customerDataList", CustomerDataList);
	        	System.out.print("Sucessfull");
	        	out.print("Sucessfull");
	        	RequestDispatcher dispatcher = request
	        			.getRequestDispatcher("login_sucessfull.jsp");
	            dispatcher.forward(request, response);
	        }else {
	        	System.out.println("Invalid Credentials");
	        	out.print("Invalid Credentials");
	        	response.sendRedirect("login_customer.jsp");
	        }
        	} catch (Throwable e) {
				
				e.printStackTrace();
			}
    }
}
