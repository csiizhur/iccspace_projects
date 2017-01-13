package com.icc.entity;

import java.io.Serializable;
/**
 * 商务合作
 * @description
 * @author zhurun
 * @date 2016年11月17日上午10:16:34
 */
public class BusinessCooperation implements Serializable {

	private static final long serialVersionUID = -8912857548877617519L;
	private String businessId;
	private String cooperationUserId;
	private String cooperationMobilePhone;
	private String cooperationContent;
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getCooperationUserId() {
		return cooperationUserId;
	}
	public void setCooperationUserId(String cooperationUserId) {
		this.cooperationUserId = cooperationUserId;
	}
	public String getCooperationMobilePhone() {
		return cooperationMobilePhone;
	}
	public void setCooperationMobilePhone(String cooperationMobilePhone) {
		this.cooperationMobilePhone = cooperationMobilePhone;
	}
	public String getCooperationContent() {
		return cooperationContent;
	}
	public void setCooperationContent(String cooperationContent) {
		this.cooperationContent = cooperationContent;
	}
	
}
