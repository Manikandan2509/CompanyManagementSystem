//$Id$
package com.companymanagement.utils;

//$Id$


import java.sql.*;

public class DbConnection {
	private static Connection con = null;
	
	public static Connection getCon() {
		
		
		
			try {
				if(con != null && !con.isClosed()) {
					return con;
				}
				Class.forName("org.postgresql.Driver");
				 con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company_management_system" ,"mani-16150","");
			}
				 
				 catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
		
		
		
		return con;
	}
	public static void closeCon() {
		if(con == null) return;
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}


