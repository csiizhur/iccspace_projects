package cn.iccspace.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1896731326259503667L;

	private String userId;
	private String openId;
	
	private String nickName;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
