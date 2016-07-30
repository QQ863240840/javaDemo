package com.ruicai.news.dao;

import java.util.List;


import com.ruicai.news.entity.Topic;

public interface TopicDao {
	boolean addTopic(String name);
	
	boolean deleteTopic(int id );
   
	boolean updateTopic(Topic topic);
	
	Topic findTopicByid(int id);
	
	List<Topic> findAllTopic() throws Exception;
}
