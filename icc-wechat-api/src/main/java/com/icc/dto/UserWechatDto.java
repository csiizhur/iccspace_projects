package com.icc.dto;

import java.io.Serializable;

import com.icc.entity.User;

public class UserWechatDto implements Serializable {

	private static final long serialVersionUID = -4341335385527521593L;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
