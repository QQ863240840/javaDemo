package com.ruicai.diary.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruicai.diary.dao.DiaryTypeDao;
import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.dao.impl.DiaryTypeDaoImpl;
import com.ruicai.diary.entity.DiaryType;

@WebServlet(name="diaryType", urlPatterns={"/diaryType"}) 
public class DiaryTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException {
		String tag = request.getParameter("action");
		//笔记类型类列表
		if(tag.equals("list")){                   
			showDiaryList(request,response);
			//增加笔记类型
		}else if(tag.equals("save")){               
			addDiaryType(request,response);
			//修改笔记类型
		}else if(tag.equals("preSave")){ 
			changeDiaryType(request,response);
			//删除笔记类型
		}else if(tag.equals("delete")){
			diaryTypeDelete(request,response);
		}
	}


	/**
	 * 笔记类型列表
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws UnsupportedEncodingException 
	 */
	private void showDiaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		DiaryTypeDaoImpl di = new DiaryTypeDaoImpl();
		List<DiaryType> diaryTypeDaoList = di.diaryTypeList();
		request.setAttribute("diaryTypeDaoList", diaryTypeDaoList);
		try {
			request.getRequestDispatcher("diaryType/diaryTypeList.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 执行增加修改笔记类型
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws UnsupportedEncodingException 
	 */

	private void addDiaryType(HttpServletRequest request, HttpServletResponse response) throws ServletException, UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		DiaryTypeDaoImpl dti =new DiaryTypeDaoImpl();
		DiaryType diarytype=new DiaryType();
		String typeName = request.getParameter("typeName");
		String diaryTypeId = request.getParameter("diaryTypeId");
		diarytype.setTypeName(typeName);
		int num=0;
		if(diaryTypeId==null||diaryTypeId==""){
			num = dti.diaryTypeAdd(typeName);
		}else{
			diarytype.setDiaryTypeId(Integer.parseInt(diaryTypeId));
			num = dti.diaryTypeUpdate(diarytype);
		}
		if(num>0){
			showDiaryList(request, response);
		}
	}


	/**
	 * 增加、修改笔记页面
	 * @throws UnsupportedEncodingException 
	 */
	private void changeDiaryType(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		DiaryTypeDaoImpl dti =new DiaryTypeDaoImpl();
		DiaryType diaryType=new DiaryType();
		String diaryTypeId=request.getParameter("diaryTypeId");
		String typeName=null;
		if(diaryTypeId!=null){
			typeName=dti.diaryTypeShow(Integer.parseInt(diaryTypeId)).getTypeName();
			diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
			diaryType.setTypeName(typeName);
			request.setAttribute("diaryType", diaryType);
		}
		try {
			request.getRequestDispatcher("diaryType/diaryTypeSave.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
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
	private void diaryTypeDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException {
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		DiaryTypeDaoImpl dti = new DiaryTypeDaoImpl();
		String diaryTypeId=request.getParameter("diaryTypeId");
		try {
			if(ddi.existDiaryWithTypeId(Integer.parseInt(diaryTypeId))){
				request.setAttribute("error", "该笔记类型存在");
			}else{
				dti.diaryTypeDelete(Integer.parseInt(diaryTypeId));
			}
			request.setAttribute("mainPage","diaryType/diaryTypeList.jsp" );
			request.getRequestDispatcher("diaryType?action=list").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}


