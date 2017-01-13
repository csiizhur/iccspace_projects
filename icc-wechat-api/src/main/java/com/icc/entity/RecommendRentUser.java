package com.icc.entity;

import java.io.Serializable;
/**
 * 推荐租户
 * @description
 * @author zhurun
 * @date 2016年11月17日上午9:59:56
 */
public class RecommendRentUser implements Serializable {

	private static final long serialVersionUID = -835726907799663614L;
	private String recommendId;
	private String recommendUserId;
	private String rentAddress;
	private String rentBrandName;
	private String rentSize;
	private String rentUserName;
	private String rentMobilePhone;
	public String getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public String getRecommendUserId() {
		return recommendUserId;
	}
	public void setRecommendUserId(String recommendUserId) {
		this.recommendUserId = recommendUserId;
	}
	public String getRentAddress() {
		return rentAddress;
	}
	public void setRentAddress(String rentAddress) {
		this.rentAddress = rentAddress;
	}
	public String getRentBrandName() {
		return rentBrandName;
	}
	public void setRentBrandName(String rentBrandName) {
		this.rentBrandName = rentBrandName;
	}
	public String getRentSize() {
		return rentSize;
	}
	public void setRentSize(String rentSize) {
		this.rentSize = rentSize;
	}
	public String getRentUserName() {
		return rentUserName;
	}
	public void setRentUserName(String rentUserName) {
		this.rentUserName = rentUserName;
	}
	public String getRentMobilePhone() {
		return rentMobilePhone;
	}
	public void setRentMobilePhone(String rentMobilePhone) {
		this.rentMobilePhone = rentMobilePhone;
	}
	
	
}
