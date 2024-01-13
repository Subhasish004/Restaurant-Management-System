package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Reservation;

public class ReservationDAO {

    public int registerData(Reservation reserve) {
        String INSERT_USER_SQL = "INSERT INTO reservation" +
                "(rdate, rtime, partysize, customername, phonenumber, tableid) values(?, ?, ?, ?, ?, ?);";
        int result = 0;

        try (Connection con = JdbcConnection.Connect()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement
            		(INSERT_USER_SQL)) {
                ps.setDate(1, reserve.getDate());
                ps.setString(2, reserve.getTime());
                ps.setInt(3, reserve.getPartySize());
                ps.setString(4, reserve.getCustomerName());
                ps.setString(5, reserve.getPhoneNumber());
                ps.setInt(6, reserve.getTableId());

                result = ps.executeUpdate();
                System.out.println("Reservation " + result);

                con.commit(); // Commit the transaction if everything is successful
            } catch (SQLException e) {
                con.rollback(); // Rollback the transaction if an error occurs
                printSQLException(e);
            }
        } catch (Throwable e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return result;
    }
   
    public Reservation ShowReservationData(int id) throws Throwable {
    	Reservation reserveData = new Reservation();
    	 try (Connection con = JdbcConnection.Connect()) {
             String findIdQuery = "SELECT * FROM reservation WHERE id = ?";
             try (PreparedStatement ps = con.prepareStatement(findIdQuery)) {
                 ps.setInt(1, id);
                 try (ResultSet rs = ps.executeQuery()) {
                	 System.out.println("Reserve Data");
                     if (rs.next()) {
                         reserveData.setId(rs.getInt(1)); 
                         reserveData.setDate(rs.getDate(2));
                         reserveData.setTime(rs.getString(3));
                         reserveData.setPartySize(rs.getInt(4));
                         reserveData.setCustomerName(rs.getString(5));
                         reserveData.setPhoneNumber(rs.getString(6));
                         reserveData.setTableId(rs.getInt(7));
                     }
                 }
             }
    	 }catch (SQLException e) {
             printSQLException(e);
         }
         return reserveData;
    }
    public int getReservationID(int tableId ,Date date,String time) throws Throwable {
        int id = -1;
        try (Connection con = JdbcConnection.Connect()) {
            String findIdQuery = "SELECT id FROM reservation WHERE tableid = ? AND rdate = ? AND rtime = ?";
            try (PreparedStatement ps = con.prepareStatement(findIdQuery)) {
            	ps.setInt(1, tableId);
	            ps.setDate(2, new java.sql.Date(date.getTime()));
	            ps.setString(3, time);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return id;
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

	

	public int updateRegisterData(Reservation reserve,int reservationId) {
		//=================================================
		String INSERT_USER_SQL = "UPDATE reservation " +
                "SET rdate = ?, rtime = ?, partysize = ? , tableid = ? " +
                "WHERE id = ?";
        int result = 0;

        try (Connection con = JdbcConnection.Connect()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement
            		(INSERT_USER_SQL)) {
                ps.setDate(1, reserve.getDate());
                ps.setString(2, reserve.getTime());
                ps.setInt(3, reserve.getPartySize());
                ps.setInt(4, reserve.getTableId());
                ps.setInt(5, reservationId);

                result = ps.executeUpdate();
                if(result==1)
                System.out.println("Update----------------------- " + result);
                else
                	System.out.print("--------------Fail--------------------");
                con.commit(); // Commit the transaction if everything is successful
            } catch (SQLException e) {
                con.rollback(); // Rollback the transaction if an error occurs
                printSQLException(e);
            }
        } catch (Throwable e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return result;
	}
	
	public Reservation getReservationById(int reservationId) {
		return null;
		
	}

	public void deleteReservation(int reservationId) throws Throwable {
		String DeleteSQL = "DELETE FROM reservation where id = ?";
		 try (Connection con = JdbcConnection.Connect()) {
	            try (PreparedStatement ps = con.prepareStatement
	            		(DeleteSQL)) {
	            	ps.setInt(1, reservationId);
	            	ps.executeUpdate();
	            }
		 }
	}
	

}//class
