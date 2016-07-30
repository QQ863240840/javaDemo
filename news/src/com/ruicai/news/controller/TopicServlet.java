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
		if(tag.equals("list")){               //��ѯ��������
			getAllTopic(request,response);
		}else if(tag.equals("save")){           //��������
			addTopic(request,response);
		}else if(tag.equals("update")){         //�޸�����
			changeTopic(request,response);
		}else if(tag.equals("updateTp")){
			  //ִ�и���	 
			updateTopic(request, response);
		}else if (tag.equals("delete"))	{
			deleteTopic(request, response);
		}
	}

	//����getAllTopic����
	private void getAllTopic(HttpServletRequest request, HttpServletResponse response){
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		//��ѯ��������
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
     * �����޸�ҳ��  
     * @param request
     * @param response
     * @throws IOException 
     */
	private void changeTopic(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//��ȡ����
		String topicId = request.getParameter("topicId");
		int tpid = Integer.parseInt(topicId);
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		//����id��ѯ����
		Topic topic = topicDaoImpl.findTopicByid(tpid);
		HttpSession session = request.getSession();
		session.setAttribute("topic",topic);
		response.sendRedirect("newspages/topic_add.jsp");	
	}
     /**
      * ִ���޸�
      * @param request
      * @param response
      */
	private void updateTopic(HttpServletRequest request,HttpServletResponse response){
		//��ȡǰ̨���ݣ���������ID����������
		//��ȡǰ̨ID
		String topicId = request.getParameter("tid");
		String topicName = request.getParameter("tname");
		int tid = Integer.parseInt("topicId");
		TopicDaoImpl topicDaoImpl = new TopicDaoImpl();
		Topic topic =  topicDaoImpl.findTopicByid(tid);
		topic.setTname("topicName");  //��������
	    boolean flag = topicDaoImpl.updateTopic(topic);
		    if(flag){
		    	// �ɹ�
		    	getAllTopic(request,response);
		    }else{
		    	System.out.println("���ʧ��");
		    }
			
		}
     
	/**
	 * ��������
	 * @throws IOException 
	 */

	 private void addTopic(HttpServletRequest request,HttpServletResponse response) throws IOException{

		  request.setCharacterEncoding("utf-8");
		  String tName = request.getParameter("tname").trim() ;  //ȥ���ո�
	      TopicDaoImpl topicDaoImpl =  new  TopicDaoImpl();
	      boolean flag = topicDaoImpl.addTopic(tName);
	      if(!flag){
	    	  response.sendRedirect("newspages/topic_list.jsp");
	      }else{
	    	  response.sendRedirect("newspages/topic_add.jsp");
	      }
       }

/**
 * ɾ������
 */

private void deleteTopic(HttpServletRequest request,HttpServletResponse response){
	
	String topicId = request.getParameter("topicId");
	int tid = Integer.parseInt(topicId);
	TopicDaoImpl topicDaoImpl =  new  TopicDaoImpl();
	boolean flag = topicDaoImpl.deleteTopic(tid);
	if(!flag){
		System.out.println("ɾ���ɹ�");
		getAllTopic(request,response);
	}else {
		System.out.println("ɾ��ʧ��");
	}
}
}
