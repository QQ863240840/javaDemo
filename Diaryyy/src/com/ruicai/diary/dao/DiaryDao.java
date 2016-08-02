package com.ruicai.diary.dao;

import java.sql.SQLException;
import java.util.List;

import com.ruicai.diary.entity.Diary;

public interface DiaryDao {
	//增加日记
	public int addDiary(Diary diary); 
	//删除日记
	public int deleteDirary(int id);
	//修改日记
	public int updateDirary(Diary diary);
	//根据ID查看笔记
	public Diary findDiaryById(int id);
	//查看所有日记
	public List<Diary> findAllDiary() throws SQLException;
	//查询某个笔记是否存在
	public boolean existDiaryWithTypeId(int typeId) throws SQLException;
}
