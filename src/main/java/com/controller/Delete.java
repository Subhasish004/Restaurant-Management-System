package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoginDAO;
import com.dao.ReservationDAO;
import com.model.Reservation;
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
private 	ReservationDAO  reservationDao;
private  LoginDAO loginDao;

public void init() throws ServletException {
    reservationDao = new ReservationDAO();
    loginDao = new LoginDAO();
}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reservationId =Integer.parseInt(request.getParameter("reservationId")) ;
		String  customerName = request.getParameter("customerName");
		try {
			reservationDao.deleteReservation(reservationId);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
		try {
			List<Reservation> CustomerDataList = loginDao.ShowCustomerData
					(customerName);
		
    	request.setAttribute("customerDataList", CustomerDataList);
    	
    	RequestDispatcher dispatcher = request
    			.getRequestDispatcher("login_sucessfull.jsp");
        dispatcher.forward(request, response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
