package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Reservation;
import com.model.Restaurant;

public class RestaurantDAO {
Restaurant restaurant = new Restaurant("Megha","Bodapolasa","9876543210",40);
    public void updateCapacity(Reservation reserve)throws Throwable{
    	try (Connection conn = JdbcConnection.Connect()){
    		String selectCapacity="SELECT capacity FROM restaurant where rname = ?";
    		 String updateQuery = "UPDATE restaurant SET capacity = ? WHERE rname = ?";
    		 PreparedStatement preparedStatement = conn.prepareStatement(selectCapacity);
             preparedStatement.setString(1, restaurant.getName()); //change parameter hotel name
             ResultSet resultSet = preparedStatement.executeQuery();
             
             int currentCapacity = 0;
	            if (resultSet.next()) {
	                currentCapacity = resultSet.getInt("capacity");
	                }
	            int reductionAmount = reserve.getPartySize() ;
	            int newCapacity = currentCapacity - reductionAmount;
	            
	            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	            updateStatement.setInt(1, newCapacity);
	            updateStatement.setString(2,  restaurant.getName());//change parameter hotel name
	            updateStatement.executeUpdate();
	            
	            
	            } catch (SQLException e) {
	                printSQLException(e);
	            }    	
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
}
