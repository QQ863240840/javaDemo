package com.ruicai.news.util;

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
		ResourceBundle rb = ResourceBundle.getBundle("com.ruicai.news.util.config");
		USER_NAME = rb.getString("USER_NAME");
		PWD = rb.getString("PWD");
		URL = rb.getString("URL");
		DRIVER_CLASS = rb.getString("DRIVER_CLASS");
		
	}
	public static Connection getConnection(){
		Connection coon = null;
		try {
			Class.forName(DRIVER_CLASS);
			try {
				coon = DriverManager.getConnection(URL,USER_NAME,PWD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return coon;
	} 
    //关闭数据库连接
	public static void closeConnection(Connection coon) throws SQLException{
		if(coon != null){
			coon.close();
		}
	}

}
