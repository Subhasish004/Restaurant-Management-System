package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.JdbcConnection;
import com.dao.*;
import com.dao.TableDAO;
import com.model.Reservation;

@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationDAO reservationDao;
   // private RestaurantDAO restaurantDao;
    public void init() throws ServletException {
        reservationDao = new ReservationDAO();
        //restaurantDao = new RestaurantDAO();
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");
        int partySize = Integer.parseInt(request.getParameter("partySize"));
        String customerName = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber");
        int tableId =  Integer.parseInt(request.getParameter("tableId"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        java.sql.Date date = null;
        try {
            java.util.Date udate = dateFormat.parse(dateStr);
            date = new java.sql.Date(udate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

            // Validate table availability
            try {
				if (TableDAO.isTableAvailable(tableId, date, timeStr)) {
				   Reservation reserve = new Reservation();
				   reserve.setCustomerName(customerName);
				   reserve.setPartySize(partySize);
				   reserve.setPhoneNumber(phoneNumber);
				   reserve.setTableId(tableId);
				   reserve.setDate(date);
				   reserve.setTime(timeStr);
				    int reservationResult = 0;
				    try {			
				        Connection con = JdbcConnection.Connect();
				        con.setAutoCommit(false);
				        reservationResult = reservationDao.registerData(reserve);
				        //restaurantDao.updateCapacity(reserve);
				        if (reservationResult == 1) {
				        	con.commit();
				        	
				        	Reservation reservationData= reservationDao.ShowReservationData
				        			(reservationDao.getReservationID(tableId, date, timeStr));
				        	request.setAttribute("Name", reservationData.getCustomerName());
				        	request.setAttribute("PhoneNo", reservationData.getPhoneNumber());
				        	request.setAttribute("Table", reservationData.getTableId());
				        	request.setAttribute("Date", reservationData.getDate());
				        	request.setAttribute("Time", reservationData.getTime());
				        	request.setAttribute("partysize", reservationData.getPartySize());
				        	request.setAttribute("id", reservationData.getId());
				        	request.setAttribute("Fail", null);
				        	RequestDispatcher dispatcher = request
				        			.getRequestDispatcher("reservationsucessful.jsp");
				            dispatcher.forward(request, response);
					    } else {
					        con.rollback();
					        System.out.print("Unsuccessful reservation ");
					        request.setAttribute("Fail", "Fail ");
					        RequestDispatcher dispatcher = request
				        			.getRequestDispatcher("reservation.jsp");
				            dispatcher.forward(request, response);
					        
					    }
				       } catch (Throwable e) {
				        e.printStackTrace();
				        response.sendRedirect("error.jsp");

				    }
				}else {		//If table unavailable  -> registration failed .jsp
					System.out.println("Table unavailable");
					request.setAttribute("Fail", "Fail");
			        RequestDispatcher dispatcher = request
		        			.getRequestDispatcher("reservation.jsp");
		            dispatcher.forward(request, response);
					
				}
			} catch (Throwable e) {
			
				e.printStackTrace();
			}
        // Handle the case when no table is available
       // out.print("No table available.");
    }

}
