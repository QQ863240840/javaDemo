package com.ruicai.diary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.ruicai.diary.dao.DiaryTypeDao;
import com.ruicai.diary.entity.DiaryType;
import com.ruicai.diary.util.DbUtil;

public class DiaryTypeDaoImpl implements DiaryTypeDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs  = null;

	/**
	 * 查看所有笔记类型
	 */
	@Override
	public List<DiaryType> diaryTypeList() {
		conn =  DbUtil.getConnection();
		String sql = "select * from t_diaryType ";
		List<DiaryType> list = new ArrayList<DiaryType>();
		try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			DiaryType diaryType = null;
			while (rs.next()){
				diaryType = new DiaryType();
				diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
				diaryType.setTypeName(rs.getString("typeName"));
				list.add(diaryType);
			}
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	   
	}

	/**
	 * 
	 */
	@Override
	public List<DiaryType> diaryTypeCountList()  {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 增加笔记类型
	 */
	@Override
	public int diaryTypeAdd(String typeName) {
		int num = 0 ;
		conn =  DbUtil.getConnection();
		String sql = "insert into t_diarytype(typeName) value(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,typeName);
			num = ps.executeUpdate();
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	
	}

	/**
	 * 更新笔记类型
	 */
	@Override
	public int diaryTypeUpdate(DiaryType diaryType)  {
		int num = 0;
		conn =  DbUtil.getConnection();
		String sql ="update t_diarytype set typeName=? where diaryTypeId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, diaryType.getTypeName());
			ps.setInt(2, diaryType.getDiaryTypeId());
		    num = ps.executeUpdate();
		
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 根据Id查看笔记类型
	 */
	@Override
	public DiaryType diaryTypeShow(int diaryTypeId)  {
		conn = DbUtil.getConnection();
		DiaryType diaryType = null;
		String sql = "select typeName from t_diaryType where diaryTypeId = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, diaryTypeId);
			rs = ps.executeQuery();
			while(rs.next()){
				diaryType = new DiaryType();
				diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
				diaryType.setTypeName(rs.getString("typeName"));
			}
			DbUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diaryType ;
	}
    
	
	/**
	 * 删除笔记类型
	 * @param diaryTypeId
	 * @return
	 * @throws Exception
	 */
	public int diaryTypeDelete(int diaryTypeId)throws Exception{
		String sql="delete from t_diaryType where diaryTypeId=?";
		conn = DbUtil.getConnection();
		ps=conn.prepareStatement(sql);
		ps.setInt(1, diaryTypeId);
	    return ps.executeUpdate();
	}
}


