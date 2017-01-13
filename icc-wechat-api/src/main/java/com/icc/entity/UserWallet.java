package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 用户钱包类
 * @author Administrator
 * @date 2016年9月12日上午10:11:41
 */
public class UserWallet implements Serializable{

	private static final long serialVersionUID = -4462850618233924061L;

	private String Id;
	private String userId;
	private int ICCB;
	private int status;
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
	public int getICCB() {
		return ICCB;
	}
	public void setICCB(int iCCB) {
		ICCB = iCCB;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
}
