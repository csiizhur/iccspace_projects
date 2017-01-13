package com.icc.dto;

import java.io.Serializable;

/**
 * 
 * @description 租户详情
 * @author zhurun
 * @date 2016年9月27日下午5:23:48
 */
public class UserShopsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238773598925468438L;
	
	private String rentUserId;
	private String shopsId;
	private String currentUserId;
	public String getRentUserId() {
		return rentUserId;
	}
	public void setRentUserId(String rentUserId) {
		this.rentUserId = rentUserId;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public String getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	

	
}
