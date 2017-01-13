package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @description 用户信息
 * @author zhur
 * @date 2016年9月6日下午4:01:32
 */
@XmlRootElement(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String openId;
	private String nickName;
	private String headImg;
	private String userName;
	private int sex;
	private int age;
	private String mobilePhone;
	private String manageHistory;
	private Timestamp subscribeTime;
	private int isSubscribe;
	private int userRole;
	private String agentUserId;
	private int deleted;
	
	private Timestamp lastAccessTime;
	
	private String ossHeadImg;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id, String openId) {
		this.userId = id;
		this.openId = openId;
	}

	
	public User(String openId, String nickName, String headImg, int sex, String mobilePhone, int deleted,
			Timestamp lastAccessTime) {
		this.openId = openId;
		this.nickName = nickName;
		this.headImg = headImg;
		this.sex = sex;
		this.mobilePhone = mobilePhone;
		this.deleted = deleted;
		this.lastAccessTime = lastAccessTime;
	}

	//@Id
	//@GeneratedValue(generator="UUID")
	@XmlElement
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		userId = id;
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
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getManageHistory() {
		return manageHistory;
	}
	public void setManageHistory(String manageHistory) {
		this.manageHistory = manageHistory;
	}
	public Timestamp getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Timestamp subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public int getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(int isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	public String getAgentUserId() {
		return agentUserId;
	}
	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public String getOssHeadImg() {
		return ossHeadImg;
	}

	public void setOssHeadImg(String ossHeadImg) {
		this.ossHeadImg = ossHeadImg;
	}
	
	
}
