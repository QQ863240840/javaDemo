package com.ruicai.diary.entity;

import java.io.Serializable;

public class DiaryType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int diaryTypeId;
	private String typeName;
	private int diaryCount;
	
	
	
	public DiaryType(String typeName) {
		super();
		this.typeName = typeName;
	}
	public DiaryType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDiaryTypeId() {
		return diaryTypeId;
	}
	public void setDiaryTypeId(int diaryTypeId) {
		this.diaryTypeId = diaryTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDiaryCount() {
		return diaryCount;
	}
	public void setDiaryCount(int diaryCount) {
		this.diaryCount = diaryCount;
	}
	
	
}
