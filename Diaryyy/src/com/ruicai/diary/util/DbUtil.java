package com.ruicai.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DbUtil {
	private static String USER_NAME;
	private static String PWD;
	private static String URL;
	private static String DRIVER_CLASS;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("com.ruicai.diary.util.config");
		USER_NAME = rb.getString("USER_NAME");
		PWD = rb.getString("PWD");
		URL = rb.getString("URL");
		DRIVER_CLASS = rb.getString("DRIVER_CLASS");
		
	}
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL,USER_NAME,PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	} 
    
	public static void closeConnection(Connection conn) throws SQLException{
		if(conn != null){
			conn.close();
		}
		
	}
  
}
