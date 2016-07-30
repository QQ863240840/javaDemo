package com.ruicai.news.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.ruicai.news.dao.TopicDao;
import com.ruicai.news.entity.Topic;
import com.ruicai.news.util.DbUtil;

public class TopicDaoImpl implements TopicDao{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
    /**
     * 添加新闻主题
     */
	@Override
	public boolean addTopic(String name) {
		//建立连接
		
		boolean flag = false;
		try {
		conn = DbUtil.getConnection();
		String sql = "insert into topic(tname) value(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			flag=ps.execute();
			
			//关闭数据库连接
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
    /**
     * 删除主题
     */
	@Override
	public boolean deleteTopic(int id) {
		boolean flag = false;
		conn = DbUtil.getConnection();
		String sql = "delete from topic where id=?";
		try {
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		flag = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;	
	}
    /**
     * 修改主题
     */
	@Override
	public boolean updateTopic(Topic topic) {
		conn = DbUtil.getConnection();
		String Sql = "update topic set tname=? where tid=? ";
		boolean flag = false;
		try {
		ps = conn.prepareStatement(Sql);
		ps.setString(1, topic.getTname());
		ps.setInt(2, topic.getTid());
		int num = ps.executeUpdate();
		if(num>0){
			flag = true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return flag;
		}
	
    /**
     * 根据ID查找主题
     */
	@Override
	public Topic findTopicByid(int id) {
		conn = DbUtil.getConnection();
		String sql = "select * from topic where tid=?" ;
		Topic topic = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs= ps.executeQuery();
		while(rs.next()){
			topic = new Topic();
			topic.setTid(rs.getInt("tid"));
			topic.setTname(rs.getString("tname"));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topic;
		
	}
    /**
     * 查询所有主题
     */
	@Override
	public List<Topic> findAllTopic()  throws Exception{
		conn = DbUtil.getConnection();
		String sql="select * from topic";
		ps = conn.prepareStatement(sql);
	    rs = ps.executeQuery();
		 
		Topic topic = null;
		List<Topic> list = new ArrayList<Topic>();
		while(rs.next()){
			topic = new Topic();
			topic.setTid(rs.getInt("tid"));
			topic.setTname(rs.getString("tname"));
			list.add(topic);	
		}
		return list;
	
		}	
		
}



