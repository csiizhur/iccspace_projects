package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @description 用户收藏商铺信息类 
 * @author zhur
 * @date 2016年9月12日下午4:03:59
 */
public class UserCollection implements Serializable {

	private static final long serialVersionUID = -7850201501365668632L;

	private String Id;
	
	private String userId;
	
	private String shopsId;
	
	private Timestamp createTime;
	private Timestamp deleteTime;
	private String deleteUserId;
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
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}
	public String getDeleteUserId() {
		return deleteUserId;
	}
	public void setDeleteUserId(String deleteUserId) {
		this.deleteUserId = deleteUserId;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
