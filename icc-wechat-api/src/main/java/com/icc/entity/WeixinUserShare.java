package com.icc.entity;

import java.io.Serializable;
/**
 * 分享model
 * @description
 * @author zhurun
 * @date 2016年12月8日下午3:08:50
 */
public class WeixinUserShare implements Serializable {

	private static final long serialVersionUID = 5837266581714315275L;
	private String userId;
	private String openId;
	private String detailId;
	private String detailUrl;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

}
