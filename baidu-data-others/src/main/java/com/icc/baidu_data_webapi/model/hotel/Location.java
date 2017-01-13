package com.icc.baidu_data_webapi.model.hotel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 坐标
 * @description
 * @author zhurun
 * @date 2016年10月19日下午1:35:39
 */
public class Location implements Serializable {

	private static final long serialVersionUID = 2413931118623703377L;

	private BigDecimal lat;//纬度
	private BigDecimal lng;//经度
	
	public Location() {
	}

	public Location(BigDecimal lat, BigDecimal lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
	
}
