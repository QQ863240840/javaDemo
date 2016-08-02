package com.ruicai.diary.dao;
import java.sql.SQLException;
import java.util.List;

import com.ruicai.diary.entity.DiaryType;
/**
 * 
 * @author
 *
 */
public interface DiaryTypeDao {
      
	public List<DiaryType> diaryTypeCountList() throws Exception;

	public List<DiaryType> diaryTypeList()throws Exception;
	
	public int diaryTypeAdd(DiaryType diaryType) throws SQLException, Exception;
	
	public int diaryTypeUpdate(DiaryType diaryType)throws Exception;		
	
	public DiaryType diaryTypeShow(String diaryTypeId)throws Exception;

	public int diaryTypeDelete(String diaryTypeId)throws Exception;
	
}
