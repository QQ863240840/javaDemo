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
/**
 * 笔记类型实现类
 * @author Zhao XiangLin
 *
 */

public class DiaryTypeDaoImpl  implements DiaryTypeDao {
	private Connection conn;
 	private PreparedStatement pstmt;
 	private ResultSet rs;
	
	 DbUtil dbUtil=new DbUtil();
	 UserDaoImpl userDaoImpl =new UserDaoImpl();
	
	// 统计每种类型的日记的数量
		public List<DiaryType> diaryTypeCountList()throws Exception{
			List<DiaryType> diaryTypeCountList=new ArrayList<DiaryType>();
			String sql="SELECT diaryTypeId,typeName,COUNT(diaryId) as diaryCount FROM t_diary RIGHT JOIN t_diaryType ON t_diary.typeId=t_diaryType.diaryTypeId GROUP BY typeName;";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DiaryType diaryType=new DiaryType();
				diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
				diaryType.setTypeName(rs.getString("typeName"));
				diaryType.setDiaryCount(rs.getInt("diaryCount"));
				diaryTypeCountList.add(diaryType);
			}
			return diaryTypeCountList;
		}
		/**
		 *  查询出所有的类型
		 * @param con
		 * @return
		 * @throws Exception
		 */
		public List<DiaryType> diaryTypeList()throws Exception{
			List<DiaryType> diaryTypeList=new ArrayList<DiaryType>();
			String sql="select * from t_diaryType";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DiaryType diaryType=new DiaryType();
				diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
				diaryType.setTypeName(rs.getString("typeName"));
				diaryTypeList.add(diaryType);
			}
			return diaryTypeList;
		}
		/**
		 * 添加日记类型 
		 * @param con
		 * @param diaryType
		 * @return
		 * @throws Exception
		 */
		public int diaryTypeAdd(DiaryType diaryType) throws Exception{
			String sql="insert into t_diaryType values(null,?)";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, diaryType.getTypeName());
			return pstmt.executeUpdate();
		}
		
		public int diaryTypeUpdate(DiaryType diaryType)throws Exception{
			String sql="update t_diaryType set typeName=? where diaryTypeId=?";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, diaryType.getTypeName());
			pstmt.setInt(2, diaryType.getDiaryTypeId());
			return pstmt.executeUpdate();
		}
		
		public DiaryType diaryTypeShow(String diaryTypeId)throws Exception{
			String sql="SELECT * from t_diaryType where diaryTypeId=?";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, diaryTypeId);
			rs=pstmt.executeQuery();
			DiaryType diaryType=new DiaryType();
			if(rs.next()){
				diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
				diaryType.setTypeName(rs.getString("typeName"));
			}
			return diaryType;
		}
		/**
		 * 删除笔记类型
		 */
		public int diaryTypeDelete(String diaryTypeId)throws Exception{
			String sql="delete from t_diaryType where diaryTypeId=?";
			conn=DbUtil.getCon();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, diaryTypeId);
			return pstmt.executeUpdate();
		}
}
