package com.ruicai.diary.entity;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable{

	private int diaryId;
	private String title;      //标题
	private String content;    //内容
	private int typeId=-1;     //类型ID
	private Date releaseDate;  //发布日期

	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Override
	public String toString() {
		return "Diary [diaryId=" + diaryId + ", title=" + title + ", content=" + content + ", typeId=" + typeId
				+ ", releaseDate=" + releaseDate + "]";
	}


}
