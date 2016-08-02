package com.ruicai.diary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruicai.diary.dao.DiaryDao;
import com.ruicai.diary.dao.DiaryTypeDao;
import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.dao.impl.DiaryTypeDaoImpl;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.entity.PageBean;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.PropertiesUtil;
import com.ruicai.diary.util.StringUtil;
/**
 *  主页面Servlet
 * @author Kevin Zhao
 *
 */
@WebServlet(urlPatterns={"/main"})
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	DiaryDaoImpl diaryDao=new DiaryDaoImpl();
	DiaryTypeDaoImpl diaryTypeDao=new DiaryTypeDaoImpl();
   
	
	/**
	 *  在主页面有三个大的区域：
	 *  1：
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		request.setCharacterEncoding("utf-8");
		//获取主页面请求参数
		HttpSession session =request.getSession();
		// 获取页数
		String page=request.getParameter("page");
		Diary diary=new Diary();
		// page为空，设置页数为1 即是首页
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		// 创建分页对象
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		try {
			//查询出所有的日记
			List<Diary> diaryList=diaryDao.diaryList(pageBean,diary);
			// 查询出所有的日记数量
			int total=diaryDao.diaryCount(diary);
			
			String pageCode=this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			//设置页码
			request.setAttribute("pageCode", pageCode);
			//所有的日记
			request.setAttribute("diaryList", diaryList);
			// 返回每种类型的日记的数量
			session.setAttribute("diaryTypeCountList", diaryTypeDao.diaryTypeCountList());
			// 按照日期分类返回每种日期下的日记有多少个
			session.setAttribute("diaryCountList", diaryDao.diaryCountList());
			// 设置请求返回的日记列表页面
			request.setAttribute("mainPage", "diary/diaryList.jsp");
			// 跳转到主页面
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

	
	/**
	 *  执行分页
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	private String genPagation(int totalNum,int currentPage,int pageSize){
		/**
		 *  三木运算符：  true :totalNum/pageSize   false:pageSize:totalNum/pageSize+1;
		 */
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<li><a href='main?page=1'>首页</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}else{
			pageCode.append("<li><a href='main?page="+(currentPage-1)+"'>上一页</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			}else{
				pageCode.append("<li><a href='main?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
		}else{
			pageCode.append("<li><a href='main?page="+(currentPage+1)+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='main?page="+totalPage+"'>尾页</a></li>");
		return pageCode.toString();
	}
	
	


}
