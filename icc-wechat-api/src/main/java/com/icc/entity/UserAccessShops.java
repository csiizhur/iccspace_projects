package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 用于谁看过我
 * @description
 * @author zhurun
 * @date 2016年11月25日下午4:39:17
 */
public class UserAccessShops implements Serializable {
	private static final long serialVersionUID = 4032401856195318758L;
	
	private String accessId;
	private String accessUserId;
	private String shopsId;
	private String userId;
	private Integer version;
	private Timestamp accessTime;
	public Timestamp getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getAccessId() {
		return accessId;
	}
	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	public String getAccessUserId() {
		return accessUserId;
	}
	public void setAccessUserId(String accessUserId) {
		this.accessUserId = accessUserId;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
