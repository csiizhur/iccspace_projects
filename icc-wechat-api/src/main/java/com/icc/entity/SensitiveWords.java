package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @description 敏感字 
 * @author zhur
 * @date 2016年9月13日上午9:23:34
 */
public class SensitiveWords implements Serializable {

	private static final long serialVersionUID = -5754858425507024037L;

	private String Id;
	private String words;
	private Timestamp deleteTime;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Timestamp getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
}
