package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBUtil {
	static Connection conn;
	public static Connection getDBConn() {
		Scanner si = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/j210ticketbookingsystem";
		String username = "root";
		String pwd = "indira";
	
//		System.out.println("Enter username:");
//		
//		String username = si.next();
//		System.out.println("Enter password:");
//		
//		String pwd = si.next();
		try {
			conn = DriverManager.getConnection(url,username,pwd);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		si.close();
		return conn;
	}
}


