package com.ruicai.diary.dao;

import java.sql.SQLException;
import com.ruicai.diary.entity.User;

public interface UserDao {
     
	 /**
	  *  处理用户登录
	  * @param con
	  * @param user
	  * @return
	  * @throws Exception
	  */
	public User login(User user) throws Exception;
	/**
	 * 更新用户信息
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userUpdate(User user)throws SQLException, Exception;
		
}
