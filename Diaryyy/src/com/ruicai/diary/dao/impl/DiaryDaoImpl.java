package com.ruicai.diary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ruicai.diary.dao.DiaryDao;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.util.DbUtil;

public class DiaryDaoImpl implements DiaryDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/**
	 * 增加日记
	 */
	@Override
	public int addDiary(Diary diary) {
		int num =0;
		conn = DbUtil.getConnection();
		String sql = "insert into t_diary (title,content,typeId,releaseDate) values (?,?,?,NOW())";  
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,diary.getTitle());
			ps.setString(2, diary.getContent());
			ps.setInt(3, diary.getTypeId());
			num =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 删除日记
	 */
	@Override
	public int deleteDirary(int id) {
		int num = 0;
		conn = DbUtil.getConnection();

		String sql = "delete from t_diary where diaryId = ?";
		try {
			ps = conn .prepareStatement(sql);
			ps.setInt(1, id);
			num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 修改日记
	 */
	@Override
	public int updateDirary(Diary diary) {
		int num=0;
		conn = DbUtil.getConnection();
		String sql = "update t_diary set title=?,content=?,typeId=?,releaseDate=now() where diaryId=? ";


		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, diary.getTitle());
			ps.setString(2, diary.getContent());
			ps.setInt(3, diary.getTypeId());
			ps.setInt(4, diary.getDiaryId());
			num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 根据ID查看笔记
	 */
	@Override
	public Diary findDiaryById(int id) {
		conn = DbUtil.getConnection();
		String sql ="select * from t_diary where diaryId=?";
		Diary diary = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				diary = new Diary();
				diary.setTitle(rs.getString("title"));
				diary.setContent(rs.getString("content"));
				diary.setTypeId(rs.getInt("typeId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diary;
	}

	/**
	 * 查看所有笔记
	 */
	@Override
	public List<Diary> findAllDiary() throws SQLException {
		conn = DbUtil.getConnection();
		String sql = "select * from t_diary";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		List<Diary> diaryList = new ArrayList<Diary>();
		Diary diary = null;
		while(rs.next()) {
			diary = new Diary();
			diary.setContent(rs.getString("content"));
			diary.setDiaryId(rs.getInt("diaryId"));
			diary.setTitle(rs.getString("title"));
			diary.setTypeId(rs.getInt("typeId"));
			diary.setReleaseDate(rs.getDate("releaseDate"));
			diaryList.add(diary);
		}
		return diaryList;
	}
	
	/**
	 *  查询某个日记是否存在
	 */
	public boolean existDiaryWithTypeId(int typeId) throws SQLException {
		conn = DbUtil.getConnection();
		String sql ="select * from t_diary where typeId=?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, typeId);
		rs=ps.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
	
}
