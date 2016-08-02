package com.ruicai.diary.dao;

import java.util.List;

import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.entity.PageBean;

public interface DiaryDao {
   
	

    /**
     *  分页查询所有的日记
     *  两个表联合查询 得到关于日记的所有的信息 包括类型等等
     * @param con
     * @param pageBean
     * @param s_diary
     * @return
     * @throws Exception
     */
	public List<Diary> diaryList(PageBean pageBean,Diary s_diary) throws Exception;
	
	
	
	
	// 查询出所有的日记数量
	public int diaryCount(Diary s_diary)throws Exception;
	
	
	
	
	public List<Diary> diaryCountList()throws Exception;
	
	
	
	/**
	 *  根据日记类型查询该日记
	 *  主表是日记
	 *  
	 * @param con
	 * @param diaryId
	 * @return
	 * @throws Exception
	 */
	public Diary diaryShow(String diaryId)throws Exception;
	
	
	/**
	 *  添加日记
	 * @param con
	 * @param diary
	 * @return
	 * @throws Exception
	 */
	public int diaryAdd(Diary diary)throws Exception;
	
	
	
	/**
	 *  删除日记
	 * @param con
	 * @param diaryId
	 * @return
	 * @throws Exception
	 */
	public int diaryDelete(String diaryId)throws Exception;
	
	
	
	/**
	 *  更新日记
	 * @param con
	 * @param diary
	 * @return
	 * @throws Exception
	 */
	public int diaryUpdate(Diary diary)throws Exception;
	
	
	
	/**
	 *  查询某个日记是否存在
	 * @param con
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public boolean existDiaryWithTypeId(String typeId)throws Exception;
}
