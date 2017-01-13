package com.icc.common.model;

import java.io.Serializable;

/**
 * 坐标
 * @description
 * @author zhurun
 * @date 2016年10月19日下午1:35:39
 */
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1162584635306863289L;
	private String lat;//纬度
	private String lng;//经度
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	
	
	
}
