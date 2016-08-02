package com.ruicai.diary.dao;

import com.ruicai.diary.entity.User;
/**
 * 
 * @author DaoDUO
 *
 */
public interface UserDao {
    /**
     * 判断用户登录
     */
	public User CheckLogin(User user) ;
	
	public User findallUserbyId( int id) ;
	
	public int userUpdate(User user) ;
	
	

}
