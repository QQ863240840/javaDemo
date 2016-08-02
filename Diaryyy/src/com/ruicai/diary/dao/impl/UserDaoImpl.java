package com.ruicai.diary.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.ruicai.diary.dao.UserDao;
import com.ruicai.diary.entity.User;
import com.ruicai.diary.util.DbUtil;

public class UserDaoImpl implements UserDao {
	Connection conn=null;
	ResultSet rs= null;
	PreparedStatement ps= null;

	/**
	 * 检查用户登录
	 */
	@Override
	public User CheckLogin(User user) {
		conn = DbUtil.getConnection();
		String sql = "select * from t_user where userName=? and password=?";
		User userInfo = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userInfo = new User();
				userInfo.setUserId(rs.getInt("userId"));
				userInfo.setUserName(rs.getString("userName"));
				//userInfo.setPassword(rs.getString("password"));
				userInfo.setNickName(rs.getString("nickName"));
				userInfo.setImageName(rs.getString("imageName"));
				userInfo.setMood(rs.getString("mood"));

			}
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return userInfo;
	}

	/**
	 * 根据ID查找用户
	 */
	@Override
	public User findallUserbyId( int id) {
		conn = DbUtil.getConnection();
		String sql = "select * from t_user where id=? ";
		User user = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				user.setUserId(rs.getInt("useId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nickName"));
				user.setImageName(rs.getString("imageName"));
				user.setMood(rs.getString("mood"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}


	/**
	 * 更新用户
	 */
	@Override
	public int userUpdate(User user) {
		int num = 0;
		conn = DbUtil.getConnection();
		String sql = "update t_user set nickName=?,imageName=?,mood=?"
				+ " where userId=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getNickName());
			ps.setString(2, user.getImageName());
			ps.setString(3, user.getMood());
			ps.setInt(4, user.getUserId());
			num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;	
	}

}
