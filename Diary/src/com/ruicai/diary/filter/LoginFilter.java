package com.ruicai.diary.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 过滤器的作用描述
 */
public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
	
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpSession session=request.getSession();
		Object object=session.getAttribute("currentUser");
		//获取请求的路径
		String path=request.getServletPath();
		// 如果在请求的路径中找不到login
		if(object==null&&path.indexOf("login")<0&&path.indexOf("bootstrap")<0&&path.indexOf("images")<0){
			response.sendRedirect("login.jsp");
		}else{
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
