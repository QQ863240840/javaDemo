package com.ruicai.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.StringUtil;
/**
 *  笔记管理
 * @author KevinZhao
 *
 */
@WebServlet(urlPatterns={"/diary"})
public class DiaryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	DiaryDaoImpl diaryDao=new DiaryDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	/**
	 *  接收业务请求然后转发到各个方法:
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("show".equals(action)){
			// 云笔记列表  查看笔记的详细信息
			diaryShow(request,response);
		}else if("addDiary".equals(action)){
			// 写笔记
			diaryEdit(request,response);
		}else if("save".equals(action)){
			// 保存笔记，修改笔记
			diarySave(request,response);
			
		}else if("delete".equals(action)){
			// 删除某个笔记
			diaryDelete(request,response);
		}
	}
	/**
	 *  查看某个具体的笔记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryShow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryId=request.getParameter("diaryId");
		try{
			//根据笔记id查看该笔记，需要把笔记类型返回。因为笔记类型位于笔记类型表所有需要连接查询
			Diary diary=diaryDao.diaryShow( diaryId);
			request.setAttribute("diary", diary);
			request.setAttribute("mainPage", "diary/diaryInfo.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   /**
    *  
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
	private void diaryEdit(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryId=request.getParameter("diaryId");// 获取笔记的Id
		try{
			// 如果笔记的ID不为空---》编辑状态
			if(StringUtil.isNotEmpty(diaryId)){
				// 查询出笔记
				Diary diary=diaryDao.diaryShow(diaryId);
				request.setAttribute("diary", diary);
			}
			// 返回编辑页面
			request.setAttribute("mainPage", "diary/diaryEdit.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 *  更新云笔记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diarySave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//新增的时候获取相应的数据  
		String title=request.getParameter("title");// 标题
		String content=request.getParameter("content");//内容
		String typeId=request.getParameter("typeId");//笔记类型Id
		//----------
		String diaryId=request.getParameter("diaryId");// 获取笔记Id
		/**
		 *  可以获取到笔记的Id，说明是编辑，
		 */
		Diary diary=null;
		if(StringUtil.isNotEmpty(diaryId)){
			// 依照获取到的内容创建笔记对象
			diary=new Diary(title,content,Integer.parseInt(typeId));
			//设置笔记Id
			diary.setDiaryId(Integer.parseInt(diaryId));
		}else{
			//新增笔记对象
			diary=new Diary(title,content,Integer.parseInt(typeId));
		}
		try {
			int saveNums=0;
			if(StringUtil.isNotEmpty(diaryId)){
				//更新笔记，把新对象的内容传入的保存到数据库
				saveNums=diaryDao.diaryUpdate(diary);	
			}else{
				//执行 新增笔记
				saveNums=diaryDao.diaryAdd(diary);				
			}
			if(saveNums>0){
				//成功之后跳转
				request.getRequestDispatcher("main?action=mainPage").forward(request, response);
			}else{
				request.setAttribute("diary", diary);
				request.setAttribute("error", "更新失败");
				request.setAttribute("mainPage", "diary/diaryEdit.jsp");
				request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  删除某个笔记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaryDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryId=request.getParameter("diaryId");
		try{
			diaryDao.diaryDelete(diaryId);
			request.getRequestDispatcher("main?action=mainPage").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
