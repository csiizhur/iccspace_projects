package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 黑名单类
 * @author zhur
 * @date 2016年9月13日上午9:07:09
 */
public class BlackList implements Serializable {

	private static final long serialVersionUID = 8064806984085306519L;

	private String Id;
	private String userId;
	private String blackUserId;
	private int blackListStatus;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBlackUserId() {
		return blackUserId;
	}
	public void setBlackUserId(String blackUserId) {
		this.blackUserId = blackUserId;
	}
	public int getBlackListStatus() {
		return blackListStatus;
	}
	public void setBlackListStatus(int blackListStatus) {
		this.blackListStatus = blackListStatus;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
}
