package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import com.model.Table;

public class TableDAO {

	public int tableData(Table table) {
		String INSERT_TABLE_SQL ="INSERT INTO  rtable" +
				"(tableid,capacity,available) values(?,?,?);";
		int result =0;
		 
		try(Connection con = JdbcConnection.Connect())
		{
			con.setAutoCommit(false);
			PreparedStatement ps= con.prepareStatement(INSERT_TABLE_SQL);
			ps.setInt(1, table.getId());
			ps.setInt(2, table.getCapacity());
			//====================
			ps.setInt(3,0 );
			
			result = ps.executeUpdate();
			System.out.println("Table "+result);
		}	
		catch (SQLException e){
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("Connection Failed");
			e1.printStackTrace();
		}
		
		return result;
	}
	 public static int[] getAllTables(int partysize) throws Throwable {
	        int[] tables = new int[50];
	        int i=0;
	        try (Connection connection = JdbcConnection.Connect();
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM rtable where ="+partysize);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	            	tables[i]=resultSet.getInt(1);
	                i++;
	                System.out.println(tables[i]);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception or log it
	        }

	        return tables;
	    }
	 public static boolean isTableAvailable(int tableId, Date date, String time) throws Throwable {
	        try (Connection connection = JdbcConnection.Connect();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "SELECT COUNT(*) FROM reservation " +
	                     "WHERE tableid = ? AND rdate = ? AND rtime = ?")) {

	            preparedStatement.setInt(1, tableId);
	            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
	            preparedStatement.setString(3, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int reservationCount = resultSet.getInt(1);
	                    System.out.println("Table Counts - "+reservationCount);
	                    return reservationCount == 0; // Table is available if no reservations exist
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception or log it
	        }

	        return false; // In case of an error, consider the table as unavailable
	    }
	public static boolean isTableAvailableEdit(	int tableId, Date date,String time) {
		try (Connection connection = JdbcConnection.Connect();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "SELECT COUNT(*) FROM reservation " +
	                     "WHERE tableid = ? AND rdate = ? AND rtime = ?")) {

	            preparedStatement.setInt(1, tableId);
	            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
	            preparedStatement.setString(3, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int reservationCount = resultSet.getInt(1);
	                    System.out.println(reservationCount);
	                    return reservationCount == 1; // Table is available 
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception or log it
	        } catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		return false;
	}
	 


}
