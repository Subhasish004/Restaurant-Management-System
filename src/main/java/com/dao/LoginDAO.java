package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Reservation;

public class LoginDAO {

	public boolean validateLogin(String username, String phonenumber) throws Throwable {
		try(Connection con = JdbcConnection.Connect()) {
			String LOGIN_USER_SQL="SELECT phonenumber FROM reservation WHERE customername = ?";
			 PreparedStatement ps = con.prepareStatement
	            		(LOGIN_USER_SQL);
			 ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
                	 System.out.println("login");
                     if (rs.next()) {
                    	 if(phonenumber.equals(rs.getString(1))) {
                    		 return true;
                    	 }else {
                    		 return false;
                    	 }
                     }
			 	
			 }
		
		return false;
	}

	public List<Reservation> ShowCustomerData(String username) throws Throwable {
		List<Reservation> customer = new ArrayList<>();
   	 try (Connection con = JdbcConnection.Connect()) {
            String CustomerQuery = "SELECT * FROM reservation WHERE customername = ?";
            try (PreparedStatement ps = con.prepareStatement(CustomerQuery)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
               	 System.out.println("Reserve Data");
                    while(rs.next()) {
                    	Reservation CustomerData = new Reservation();
                    	CustomerData.setId(rs.getInt(1)); 
                    	CustomerData.setDate(rs.getDate(2));
                    	CustomerData.setTime(rs.getString(3));
                        CustomerData.setPartySize(rs.getInt(4));
                        CustomerData.setCustomerName(rs.getString(5));
                        CustomerData.setPhoneNumber(rs.getString(6));
                        CustomerData.setTableId(rs.getInt(7));
                        customer.add(CustomerData);
                    }
                }
            }
   	 }catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
}

private void printSQLException(SQLException ex) {
    ex.printStackTrace(System.err);

    System.err.println("SQLState: " + ex.getSQLState());
    System.err.println("Error code: " + ex.getErrorCode());
    System.err.println("Message: " + ex.getMessage());

    Throwable t = ex.getCause();
    while (t != null) {
        System.out.println("Cause: " + t);
        t = t.getCause();
    }
}

}//class