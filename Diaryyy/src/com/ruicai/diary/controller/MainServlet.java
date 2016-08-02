package com.ruicai.diary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.entity.PageBean;
import com.ruicai.diary.util.StringUtil;


@WebServlet(name="main", urlPatterns={"/main"}) 
public class MainServlet extends HttpServlet{

	/**
	 * 主页面
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		try {
		List<Diary> diaryList = ddi.findAllDiary();
//		HttpSession session = request.getSession();
//		session.setAttribute("diaryList",diaryList);
//		session.setAttribute("mainPage", "diary/diaryList.jsp");
//		response.sendRedirect("mainTemp.jsp");
		request.setAttribute("diaryList", diaryList);
 		request.setAttribute("mainPage", "diary/diaryList.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 *  执行分页
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @throws SQLException 
	 */
	private String genPagation( HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException{
	   
		// 获取页数
				String pag =request.getParameter("page");
						// page为空，设置页数为1 即是首页
				if(StringUtil.isEmpty(pag)){
					pag="1";
				}
				int page =Integer.parseInt(pag);
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		List<Diary> clist= ddi.findAllDiary();//将查询结果存放在List集合里  
		PageBean pagebean=new PageBean(clist.size());//初始化PageBean对象  
		//设置当前页  
		pagebean.setCurPage(page); //这里page是从页面上获取的一个参数，代表页数  
		//获得分页大小  
		int pagesize=pagebean.getPageSize();  
		//获得分页数据在list集合中的索引  
		int firstIndex=(page-1)*pagesize;  
		int toIndex=page*pagesize;  
		if(toIndex>clist.size()){  
		    toIndex=clist.size();  
		}  
		if(firstIndex>toIndex){  
		    firstIndex=0;  
		    pagebean.setCurPage(1);  
		}  
		//截取数据集合，获得分页数据  
		List<Diary> courseList=clist.subList(firstIndex, toIndex);
		return pag;  

   }
}