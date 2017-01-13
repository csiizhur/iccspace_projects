package com.icc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @description 经纪人信息
 * @author zhur
 * @date 2016年9月13日上午8:58:41
 */
public class AgentUserInfo implements Serializable {

	private static final long serialVersionUID = -8086812293342123213L;

	private String userId;
	private String identityId;
	private String mobile;
	private String userName;
	private String photo;
	private Date createDate;
	private Date updateDate;
	private String createNo;
	private String updateNo;
	private int deleted;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateNo() {
		return createNo;
	}
	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}
	public String getUpdateNo() {
		return updateNo;
	}
	public void setUpdateNo(String updateNo) {
		this.updateNo = updateNo;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
