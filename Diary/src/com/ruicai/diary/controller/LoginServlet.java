package com.ruicai.diary.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruicai.diary.dao.UserDao;
import com.ruicai.diary.dao.impl.UserDaoImpl;
import com.ruicai.diary.entity.User;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.PropertiesUtil;
/**
 *  处理用户登录
 * @author Kevin Zhao
 *
 */
@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDaoImpl userDaoImpl =new UserDaoImpl();
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String remember=request.getParameter("remember");
		
		try{
			User user=new User(userName,password);
			// 查询该用户是否存在
			User currentUser=userDaoImpl.login(user);
			if(currentUser==null){
				request.setAttribute("user", user);
				request.setAttribute("error", "用户不存在");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				if("remember-me".equals(remember)){
					rememberMe(userName,password,response);
				}
				// 设置当前用户信息
				session.setAttribute("currentUser", currentUser);
				String userPic =PropertiesUtil.getValue("imagePath")+currentUser.getImageName();//获取图片的名称
				session.setAttribute("userPic", userPic);
				System.out.println("头像路径"+PropertiesUtil.getValue("imagePath")+userPic);
				// 跳转到主页面的Servlet
				request.getRequestDispatcher("main").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
  /**
   *  记住我
   * @param userName
   * @param password
   * @param response
   */
	private void rememberMe(String userName,String password,HttpServletResponse response){
		Cookie user=new Cookie("user",userName+"-"+password);
		user.setMaxAge(1*60*60*24*7);
		response.addCookie(user);
	}
}
