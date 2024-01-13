package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	public static Connection Connect() throws Throwable {
		//JDBC
				String url = "jdbc:mysql://localhost:3306/hotelmanagement";
				String userName = "root";
				String password = "root";
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Establish  a connection
					Connection con = DriverManager.getConnection(url, userName, password);
				System.out.println("Connection Established");
			return con;
	}
}
