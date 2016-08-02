package com.ruicai.diary.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruicai.diary.dao.DiaryDao;
import com.ruicai.diary.dao.DiaryTypeDao;
import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.dao.impl.DiaryTypeDaoImpl;
import com.ruicai.diary.entity.DiaryType;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.StringUtil;
/**
 *  笔记分类管理
 * @author Kevin Zhao
 *
 */
@WebServlet(urlPatterns={"/diaryType"})
public class DiaryTypeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	DiaryTypeDao diaryTypeDao=new DiaryTypeDaoImpl();
	DiaryDaoImpl diaryDao=new DiaryDaoImpl();
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("list".equals(action)){
			// 云笔记分类列表----》返回笔记类别列表
			diaryTypeList(request,response);
		}else if("changeType".equals(action)){
			// 修改日记类型：
			diaryTypePreSave(request,response);
		}else if("save".equals(action)){
			//保存和修改
			diaryTypeSave(request,response);
		}else if("delete".equals(action)){
			//删除笔记类型：
			diaryTypeDelete(request,response);
		}
	}


	/**
	 *  返回云笔记列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryTypeList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try{
			//查询所有的类型
			List<DiaryType> diaryTypeList=diaryTypeDao.diaryTypeList();
			request.setAttribute("diaryTypeList", diaryTypeList);
			//设置转发的数据页面
			request.setAttribute("mainPage", "diaryType/diaryTypeList.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 *  添加和修改：
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryTypePreSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryTypeId=request.getParameter("diaryTypeId");
		if(StringUtil.isNotEmpty(diaryTypeId)){
			try{
				DiaryType diaryType=diaryTypeDao.diaryTypeShow(diaryTypeId);
				request.setAttribute("diaryType", diaryType);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("mainPage", "diaryType/diaryTypeEdit.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
	}
	
	/**
	 *  保存和修改：
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryTypeSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryTypeId=request.getParameter("diaryTypeId");
		String typeName=request.getParameter("typeName");
		DiaryType diaryType=new DiaryType(typeName);
		if(StringUtil.isNotEmpty(diaryTypeId)){
			diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
		}
		try{
			int saveNum=0;
			if(StringUtil.isNotEmpty(diaryTypeId)){
				saveNum=diaryTypeDao.diaryTypeUpdate(diaryType);
			}else{
				saveNum=diaryTypeDao.diaryTypeAdd( diaryType);
			}
			if(saveNum>0){
				request.getRequestDispatcher("diaryType?action=list").forward(request, response);
			}else{
				request.setAttribute("diaryType", diaryType);
				request.setAttribute("error", "修改笔记类型失败");
				request.setAttribute("mainPage", "diaryType/diaryTypeEdit.jsp");
				request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 *  删除笔记类型：
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryTypeDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryTypeId=request.getParameter("diaryTypeId");
		try{
			if(diaryDao.existDiaryWithTypeId(diaryTypeId)){
				request.setAttribute("error", "该笔记类型存在");
			}else{
				diaryTypeDao.diaryTypeDelete( diaryTypeId);
			}
			request.getRequestDispatcher("diaryType?action=list").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
