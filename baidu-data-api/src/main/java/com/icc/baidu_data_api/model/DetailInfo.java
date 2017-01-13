package com.icc.baidu_data_api.model;

import java.io.Serializable;

public class DetailInfo implements Serializable {
	
	private static final long serialVersionUID = 3290820524885638255L;

	private int distance;
	private String tag;//房地产;住宅区
	private String type;
	private String detail_url;
	private String image_num;
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	
}
