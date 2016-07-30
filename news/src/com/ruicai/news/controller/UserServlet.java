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

//Servletע��
@WebServlet(name="UserServlet", urlPatterns={"/UserServlet"})  
public class UserServlet extends HttpServlet {
//���л�
private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	     //1����ȡ�û���������                                                                          // .trim()ȥ���ո�
	      String userName =request.getParameter("username").trim();
	      String userPass =request.getParameter("upwd").trim(); 
	      //2����֤�û�����
	      User user =new User();
	      user.setUname(userName);
	      user.setUpwd(userPass);
	      UserDaoImpl userDaoimpl =new UserDaoImpl();
	      User userInfo = userDaoimpl.checkLogin(user);
	       if(userInfo==null){
	    	   response.sendRedirect("index.jsp");
	       }else{
	    	   //3�� ����Session
		    	  HttpSession session = request.getSession();
		    	  session.setAttribute("userName", userName);
		    	  //4�� ��תҳ��
		           response.sendRedirect("newspages/admin.jsp");
		      //   request.getRequestDispatcher("newspages/admin.jsp").forward(request, response);
	       }
	     	
	}

}
