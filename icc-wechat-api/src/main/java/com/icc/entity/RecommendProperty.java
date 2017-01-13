package com.icc.entity;

import java.io.Serializable;
/**
 * 推荐房源
 * @description
 * @author zhurun
 * @date 2016年11月17日上午9:59:19
 */
public class RecommendProperty implements Serializable {

	private static final long serialVersionUID = -5680310707146415593L;
	
	private String recommendId;
	private String recommendUserId;
	private String propertyAddress;
	private String propertySize;
	private String propertyUserName;
	private String propertyMobilePhone;
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
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getPropertySize() {
		return propertySize;
	}
	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}
	public String getPropertyUserName() {
		return propertyUserName;
	}
	public void setPropertyUserName(String propertyUserName) {
		this.propertyUserName = propertyUserName;
	}
	public String getPropertyMobilePhone() {
		return propertyMobilePhone;
	}
	public void setPropertyMobilePhone(String propertyMobilePhone) {
		this.propertyMobilePhone = propertyMobilePhone;
	}
	
}
