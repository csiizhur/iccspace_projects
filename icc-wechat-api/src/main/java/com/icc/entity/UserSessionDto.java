package com.icc.entity;

import java.io.Serializable;

public class UserSessionDto implements Serializable{

	private static final long serialVersionUID = 7088979277487495747L;
	
	private String userId;
	private String openId;
	private int userRole;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	
}
