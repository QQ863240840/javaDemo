package com.ruicai.diary.controller;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ruicai.diary.dao.impl.UserDaoImpl;
import com.ruicai.diary.entity.User;
import com.ruicai.diary.util.DateUtil;
import com.ruicai.diary.util.DbUtil;
/**
 *  用户个人信息管理
 */
@WebServlet(urlPatterns={"/user"})
public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	UserDaoImpl userDaoImpl =new UserDaoImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("userMain".equals(action)){
			// 进入用户修改页面
			userPreSave(request,response);
		}else if("save".equals(action)){
			// 保存用户信息
		   try {
			userSave(request,response);
		  } catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		}
	}

	/**
	 * 个人信息编辑
	 * 该方法跳转到用户个人信息编辑的页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userPreSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			request.setAttribute("mainPage", "user/userSave.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);		
	}
	/**
	 *  用户个人中心
	 *  用户头像上传
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException 
	 */
	private void userSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, FileUploadException {
		DiskFileItemFactory factory=new DiskFileItemFactory();//创建磁盘工厂
		//ServletFileUpload 处理表单数据，将数据封装到 FileItem 对象中。
		//上传文件的所有数据都在FileItem中
		ServletFileUpload upload=new ServletFileUpload(factory);
		//使用集合接收，因为上传的可能不仅仅是一个文件
		List<FileItem> items=upload.parseRequest(request);//获取所有的上传域列表;
		// 解析request请求中的数据
		Iterator<FileItem> itr=items.iterator();
		User user=(User)request.getSession().getAttribute("currentUser");
		System.out.println(user);
		boolean imageChange=false;
		// 对集合数据进行遍历
		while(itr.hasNext()){
			//依次取出数据放到文件对象中
			FileItem item=(FileItem)itr.next();
			//普通表单元素
			if(item.isFormField()){
				String fieldName=item.getFieldName();//获取表单值
				if(fieldName.equals("nickName")){
					//以指定的编码保存昵称数据
					user.setNickName(item.getString("utf-8"));
				}else if(fieldName.equals("mood")){
					// 以指定的编码保存心情数据
					user.setMood(item.getString("utf-8"));
				}
			}else if(!"".equals(item.getName())){ // 获取上传文件的字段名
				try{
					imageChange=true;
					
					String imageName=DateUtil.getCurrentDateStr();
					String realPath=getServletContext().getRealPath("/userImages");
					System.out.println("路径"+realPath);
					String imageFile=imageName+"."+item.getName().split("\\.")[1];
					user.setImageName(imageFile);//为用户设置头像
					System.out.println(imageFile);
					//创建指定路径
					File saveFile=new File(realPath,imageFile);
					item.write(saveFile);
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		if(!imageChange){
			// replaceFirst, 替换第一个子字符串
		     user.setImageName(user.getImageName());
		}
		
		try {
			int saveNums=userDaoImpl.userUpdate(user);
			if(saveNums>0){
				user.setImageName(user.getImageName());
				request.getSession().setAttribute("currentUser", user);
				request.getRequestDispatcher("main?all=mainPage").forward(request, response);
			}else{
				request.setAttribute("currentUser", user);
				request.setAttribute("error", "更新用户信息失败");
				request.setAttribute("mainPage", "user/userSave.jsp");
				request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
