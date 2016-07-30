package com.ruicai.news.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruicai.news.dao.impl.TopicDaoImpl;
import com.ruicai.news.entity.Topic;

@WebServlet(name="topicServlet",urlPatterns={"/topicServlet"})
public class TopicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("action");
		if(tag.equals("list")){               //查询所有主题
			getAllTopic(request,response);
		}else if(tag.equals("save")){           //增加主题
			addTopic(request,response);
		}else if(tag.equals("update")){         //修改主题
			changeTopic(request,response);
		}else if(tag.equals("updateTp")){
			  //执行更新	 
			updateTopic(request, response);
		}else if (tag.equals("delete"))	{
			deleteTopic(request, response);
		}
	}

	//定义getAllTopic方法
	private void getAllTopic(HttpServletRequest request, HttpServletResponse response){
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		//查询所有主题
		List<Topic> topicList;
		try {
			topicList = topicDaoImpl.findAllTopic();
			HttpSession session = request.getSession();
			session.setAttribute("topicList", topicList);
			response.sendRedirect("newspages/topic_list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    }
	
	
    /**
     * 进入修改页面  
     * @param request
     * @param response
     * @throws IOException 
     */
	private void changeTopic(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取主题
		String topicId = request.getParameter("topicId");
		int tpid = Integer.parseInt(topicId);
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		//根据id查询主题
		Topic topic = topicDaoImpl.findTopicByid(tpid);
		HttpSession session = request.getSession();
		session.setAttribute("topic",topic);
		response.sendRedirect("newspages/topic_add.jsp");	
	}
     /**
      * 执行修改
      * @param request
      * @param response
      */
	private void updateTopic(HttpServletRequest request,HttpServletResponse response){
		//获取前台数据，包含主题ID，主题内容
		//获取前台ID
		String topicId = request.getParameter("tid");
		String topicName = request.getParameter("tname");
		int tid = Integer.parseInt("topicId");
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		Topic topic =  topicDaoImpl.findTopicByid(tid);
		topic.setTname("topicName");  //更新主题
	    boolean flag = topicDaoImpl.updateTopic(topic);
		    if(flag){
		    	// 成功
		    	getAllTopic(request,response);
		    }else{
		    	System.out.println("添加失败");
		    }
			
		}
     
	/**
	 * 增加主题
	 * @throws IOException 
	 */

	 private void addTopic(HttpServletRequest request,HttpServletResponse response) throws IOException{

		  request.setCharacterEncoding("utf-8");
		  String tName = request.getParameter("tname").trim() ;  //去除空格
	      TopicDaoImpl topicDaoImpl =  new  TopicDaoImpl();
	      boolean flag = topicDaoImpl.addTopic(tName);
	      if(!flag){
	    	  response.sendRedirect("newspages/topic_list.jsp");
	      }else{
	    	  response.sendRedirect("newspages/topic_add.jsp");
	      }
       }

/**
 * 删除主题
 */

private void deleteTopic(HttpServletRequest request,HttpServletResponse response){
	
	String topicId = request.getParameter("topicId");
	int tid = Integer.parseInt(topicId);
	TopicDaoImpl topicDaoImpl =  new  TopicDaoImpl();
	boolean flag = topicDaoImpl.deleteTopic(tid);
	if(!flag){
		System.out.println("删除成功");
		getAllTopic(request,response);
	}else {
		System.out.println("删除失败");
	}
}
}
