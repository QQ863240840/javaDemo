package com.ruicai.news.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.ruicai.news.dao.UserDao;
import com.ruicai.news.entity.User;
import com.ruicai.news.util.DbUtil;


public class UserDaoImpl implements UserDao{
	private Connection conn;
	/**
     *  连接数据库查询数据
     */
	@Override
	public User checkLogin(User user) {
		// 建立连接
		 conn =(Connection) DbUtil.getConnection();
		  User userInfo =null;
		 //写SQL
		 String sql="select uid,uname,upwd from t_user where uname=? and upwd= ?";
		 // 
			try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		    ps.setString(1, user.getUname());
		    ps.setString(2, user.getUpwd());
		    ResultSet rs = ps.executeQuery();
				while(rs.next()){
					userInfo =new User();
					userInfo.setUid(rs.getInt("uid"));
					userInfo.setUname(rs.getString("uname"));
					userInfo.setUpwd(rs.getString("upwd"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		    return userInfo;
	}

}