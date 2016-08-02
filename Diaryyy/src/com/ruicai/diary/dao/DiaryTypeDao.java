package com.ruicai.diary.dao;

import java.util.List;

import com.ruicai.diary.entity.DiaryType;

public interface DiaryTypeDao {
	
	public List<DiaryType> diaryTypeCountList() ;
    //查看所有笔记类型
	public List<DiaryType> diaryTypeList();
	//添加笔记类型
	public int diaryTypeAdd(String typeName) ;
	//更新笔记类型
	public int diaryTypeUpdate(DiaryType diaryType);		
	//根据ID展示笔记
	public DiaryType diaryTypeShow(int diaryTypeId);
    //删除笔记类型
	public int diaryTypeDelete(int diaryTypeId)throws Exception;


	
}


