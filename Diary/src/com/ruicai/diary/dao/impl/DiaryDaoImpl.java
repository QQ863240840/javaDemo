package com.ruicai.diary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruicai.diary.dao.DiaryDao;
import com.ruicai.diary.dao.DiaryTypeDao;
import com.ruicai.diary.entity.Diary;
import com.ruicai.diary.entity.PageBean;
import com.ruicai.diary.util.DateUtil;
import com.ruicai.diary.util.DbUtil;
import com.ruicai.diary.util.StringUtil;

public class DiaryDaoImpl  implements DiaryDao {
	
	private Connection conn;
 	private PreparedStatement pstmt;
 	private ResultSet rs;
 	
   /**
    * 
    */
	public List<Diary> diaryList(PageBean pageBean, Diary s_diary) throws Exception {
		List<Diary> diaryList=new ArrayList<Diary>();
		StringBuffer sb=new StringBuffer("select * from t_diary t1,t_diaryType t2 where t1.typeId=t2.diaryTypeId ");
		if(StringUtil.isNotEmpty(s_diary.getTitle())){
			sb.append(" and t1.title like '%"+s_diary.getTitle()+"%'");
		}
		if(s_diary.getTypeId()!=-1){
			sb.append(" and t1.typeId="+s_diary.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_diary.getReleaseDateStr())){
			sb.append(" and DATE_FORMAT(t1.releaseDate,'%Y年%m月')='"+s_diary.getReleaseDateStr()+"'");
		}
		sb.append(" order by t1.releaseDate desc");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sb.toString());
		rs=pstmt.executeQuery();
		while(rs.next()){
			Diary diary=new Diary();
			diary.setDiaryId(rs.getInt("diaryId"));
			diary.setTitle(rs.getString("title"));
			diary.setContent(rs.getString("content"));
			diary.setReleaseDate(DateUtil.formatString(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
			diaryList.add(diary);
		}
		return diaryList;
	}
  /**
   * 
   */
	public int diaryCount(Diary s_diary) throws Exception {
		StringBuffer sb=new StringBuffer("select count(*) as total from t_diary t1,t_diaryType t2 where t1.typeId=t2.diaryTypeId ");
		if(StringUtil.isNotEmpty(s_diary.getTitle())){
			sb.append(" and t1.title like '%"+s_diary.getTitle()+"%'");
		}
		if(s_diary.getTypeId()!=-1){
			sb.append(" and t1.typeId="+s_diary.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_diary.getReleaseDateStr())){
			sb.append(" and DATE_FORMAT(t1.releaseDate,'%Y年%m月')='"+s_diary.getReleaseDateStr()+"'");
		}
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sb.toString());
		rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

	public List<Diary> diaryCountList() throws Exception {
		List<Diary> diaryCountList=new ArrayList<Diary>();
		String sql="select date_format(releaseDate,'%Y年%m月') as releaseDateStr ,COUNT(*) AS diaryCount  FROM t_diary GROUP BY DATE_FORMAT(releaseDate,'%Y年%m月') ORDER BY DATE_FORMAT(releaseDate,'%Y年%m月') DESC;";
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		while(rs.next()){
			Diary diary=new Diary();
			diary.setReleaseDateStr(rs.getString("releaseDateStr"));
			diary.setDiaryCount(rs.getInt("diaryCount"));
			diaryCountList.add(diary);
		}
		return diaryCountList;
	}
	/**
	 *  根据日记类型查询该日记
	 *  主表是日记
	 *  
	 * @param con
	 * @param diaryId
	 * @return
	 * @throws Exception
	 */
	public Diary diaryShow(String diaryId) throws Exception {
      // 多表进行连接查询， 日记表和日记类型表
		String sql="select * from t_diary t1,t_diaryType t2 where t1.typeId=t2.diaryTypeId and t1.diaryId=?";
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, diaryId);
		rs=pstmt.executeQuery();
		Diary diary=new Diary();
		if(rs.next()){
			diary.setDiaryId(rs.getInt("diaryId"));
			diary.setTitle(rs.getString("title"));
			diary.setContent(rs.getString("content"));
			diary.setTypeId(rs.getInt("typeId"));
			diary.setTypeName(rs.getString("typeName"));
			diary.setReleaseDate(DateUtil.formatString(rs.getString("releaseDate"),"yyyy-MM-dd HH:mm:ss"));
		}
		return diary;
	}
	/**
	 *  添加日记
	 * @param con
	 * @param diary
	 * @return
	 * @throws Exception
	 */
	public int diaryAdd(Diary diary) throws Exception {
		String sql="insert into t_diary values(null,?,?,?,now())";
		conn=DbUtil.getCon();
		/**
		 *  1: sql  now()   直接insert   ： datetime:
		 *                                timestamp
		 *  
		 *  2: java代码里面： date  -->SimpleDateFormat格式化--》String 
		 *     数据库里字段设置为String 
		 */
		Date date =new Date();
		SimpleDateFormat sdf =new  SimpleDateFormat("yyyy -MM-dd HH:mm:ss");
		sdf.format(date);
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, diary.getTitle());
		pstmt.setString(2, diary.getContent());
		pstmt.setInt(3, diary.getTypeId());
		return pstmt.executeUpdate();
	}

	/**
	 *  删除日记
	 * @param con
	 * @param diaryId
	 * @return
	 * @throws Exception
	 */
	public int diaryDelete(String diaryId) throws Exception {
		String sql="delete from t_diary where diaryId=?";
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, diaryId);
		return pstmt.executeUpdate();
	}
	/**
	 *  更新日记
	 * @param con
	 * @param diary
	 * @return
	 * @throws Exception
	 */
	public int diaryUpdate(Diary diary) throws Exception {
		String sql="update t_diary set title=?,content=?,typeId=? where diaryId=?";
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, diary.getTitle());
		pstmt.setString(2, diary.getContent());
		pstmt.setInt(3, diary.getTypeId());
		pstmt.setInt(4, diary.getDiaryId());
		return pstmt.executeUpdate();
	}
	/**
	 *  查询某个日记是否存在
	 * @param con
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	public boolean existDiaryWithTypeId(String typeId) throws Exception {
		String sql="select * from t_diary where typeId=?";
		conn=DbUtil.getCon();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, typeId);
		rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
}
