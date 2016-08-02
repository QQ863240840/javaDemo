package com.ruicai.diary.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;

import com.ruicai.diary.dao.impl.DiaryDaoImpl;
import com.ruicai.diary.dao.impl.DiaryTypeDaoImpl;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.entity.DiaryType;

@WebServlet(name="write", urlPatterns={"/write"})
public class DiaryServlet extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String tag = request.getParameter("action");
		if (tag.equals("writepage")){     //增加列表
			writepage(request,response);  
		}else if(tag.equals("save")){
			String tig = request.getParameter("diaryId");
			if(tig==null||tig==""){
			try {
				addDiary(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //写笔记
			}else{
			updateDiary(request,response);//修改笔记
			}
		}else if(tag.equals("list")){ 
			showDiary(request,response);
		}else if(tag.equals("modify")) {    //显示日志内容
			modifyDiary(request, response);
		}else if(tag.equals("deleteDiary")){
			deleteDiary(request,response);  //删除笔记
		}else if(tag.equals("changeDiary")){
			changeDiary(request, response);
		}
	}

    //写日记页面
     public void writepage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	DiaryTypeDaoImpl ddi = new DiaryTypeDaoImpl();
    	List<DiaryType> diaryTypeCountList = ddi.diaryTypeList();
		request.setAttribute("diaryTypeCountList", diaryTypeCountList);
    	request.setAttribute("mainPage", "diary/diaryEdit.jsp");
 		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
 		
     }

	//查看所有笔记
	public void showDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		DiaryTypeDaoImpl dti = new DiaryTypeDaoImpl();
		List<DiaryType> diaryTypeCountList = dti.diaryTypeList();
		request.setAttribute("diaryTypeCountList", diaryTypeCountList);
		request.setAttribute("mainPage", "diary/diaryList.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
        
	}

	/**
	 * 添加笔记
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void addDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String typeId = request.getParameter("typeId");      //笔记类型ID
		Diary diary = new Diary();
		diary.setTitle(title);
		diary.setContent(content);
		diary.setTypeId(Integer.parseInt(typeId));
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		int num = ddi.addDiary(diary);
		request.setAttribute("diary", diary);
		   if(num>0){
			   DiaryDaoImpl diaryDaoImpl = new DiaryDaoImpl();
			   List<Diary> diaryList = diaryDaoImpl.findAllDiary();
			   request.setAttribute("diaryList", diaryList);
				request.setAttribute("diary", diary);
				request.setAttribute("mainPage", "diary/diaryList.jsp");
				showDiary(request,response);
				request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		   }
	}

	/**
	 * 
	 * 修改笔记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 */
	public void updateDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String diaryId = request.getParameter("diaryId");
		String diaryName = request.getParameter("diaryName");
		String content = request.getParameter("content");
		
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		Diary diary = new Diary();
		diary.setTypeId(Integer.parseInt(diaryId));
		diary.setTitle(diaryName);
		diary.setContent(content);
		int num = ddi.updateDirary(diary);
		if(num>0){
			showDiary(request,response);
		}else{
			System.out.println("修改失败");
		}
	}
	
	/**
	 * 根据ID显示 日志
	 */
	public void modifyDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String diaryId = request.getParameter("diaryId");   //获取前台ID
		int diaryid = Integer.parseInt(diaryId);
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		Diary diary = ddi.findDiaryById(diaryid);           //前台ID传到后台   然后查询数据库
		request.setAttribute("diary", diary);
		request.setAttribute("mainPage", "diary/diaryShow.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
	}
	
	/**
	 * 删除笔记 后 显示所有笔记
	 */
	public void deleteDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String diaryId = request.getParameter("diaryId");   //获取前台ID
		DiaryDaoImpl ddi = new DiaryDaoImpl();
		int diary = ddi.deleteDirary(Integer.parseInt(diaryId)); 
		request.getRequestDispatcher("main?action=mainPage").forward(request, response);
    }
	
	/**
	 * 修改日志
	 */
	public void changeDiary(HttpServletRequest request, HttpServletResponse response)throws ServletException{

		
		
   } 
}




