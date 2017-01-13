package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 广告位申请
 * @author zhur
 * @date 2016年9月12日下午6:01:52
 */
public class AdvertisementApplications implements Serializable {

	private static final long serialVersionUID = -2650932739940533651L;

	private String Id;
	
	private String applyUserId;
	
	private int applyType;
	private String applyContent;
	private String mobile;
	private int applyStatus;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public int getApplyType() {
		return applyType;
	}
	public void setApplyType(int applyType) {
		this.applyType = applyType;
	}
	public String getApplyContent() {
		return applyContent;
	}
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
