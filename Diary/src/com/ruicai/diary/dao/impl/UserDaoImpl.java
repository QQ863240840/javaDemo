package com.ruicai.diary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruicai.diary.dao.UserDao;
import com.ruicai.diary.entity.User;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.PropertiesUtil;

public class UserDaoImpl implements UserDao {
	 	private Connection conn;
	 	private PreparedStatement pstmt;
	 	private ResultSet rs;
	   /**
		  *  处理用户登录
		  * @param con
		  * @param user
		  * @return
		  * @throws Exception
		  */
		public User login(User user) throws Exception{
			User resultUser=null;
			conn=DbUtil.getCon();
			String sql="select * from t_user where userName=? and password=?";
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getPassword());
				//执行查询
				rs=pstmt.executeQuery();
				if(rs.next()){
					//设置用户数据
					resultUser=new User();
					resultUser.setUserId(rs.getInt("userId"));
					resultUser.setUserName(rs.getString("userName"));
					resultUser.setPassword(rs.getString("password"));
					resultUser.setNickName(rs.getString("nickName"));
					//resultUser.setImageName(PropertiesUtil.getValue("imageFile")+rs.getString("imageName"));
					resultUser.setImageName(rs.getString("imageName"));
					resultUser.setMood(rs.getString("mood"));
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultUser;
		}
		/**
		 * 更新用户信息
		 * @param con
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public int userUpdate(User user) throws Exception{
			String sql="update t_user set nickName=?,imageName=?,mood=? where userId=?";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getNickName());
			pstmt.setString(2, user.getImageName());
			pstmt.setString(3, user.getMood());
			pstmt.setInt(4, user.getUserId());
			return pstmt.executeUpdate();
		}
	

}
