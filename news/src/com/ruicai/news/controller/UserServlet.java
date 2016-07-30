package com.ruicai.news.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruicai.news.dao.impl.UserDaoImpl;
import com.ruicai.news.entity.User;

//Servlet注解
@WebServlet(name="UserServlet", urlPatterns={"/UserServlet"})  
public class UserServlet extends HttpServlet {
//序列化
private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	     //1：获取用户名和密码                                                                          // .trim()去除空格
	      String userName =request.getParameter("username").trim();
	      String userPass =request.getParameter("upwd").trim(); 
	      //2：验证用户数据
	      User user =new User();
	      user.setUname(userName);
	      user.setUpwd(userPass);
	      UserDaoImpl userDaoimpl =new UserDaoImpl();
	      User userInfo = userDaoimpl.checkLogin(user);
	       if(userInfo==null){
	    	   response.sendRedirect("index.jsp");
	       }else{
	    	   //3： 设置Session
		    	  HttpSession session = request.getSession();
		    	  session.setAttribute("userName", userName);
		    	  //4： 跳转页面
		           response.sendRedirect("newspages/admin.jsp");
		      //   request.getRequestDispatcher("newspages/admin.jsp").forward(request, response);
	       }
	     	
	}

}
