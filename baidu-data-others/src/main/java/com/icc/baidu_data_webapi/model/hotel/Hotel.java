package com.icc.baidu_data_webapi.model.hotel;

import java.io.Serializable;

/**
 * 饭店
 * @description
 * @author zhurun
 * @date 2016年10月19日下午1:34:39
 */
public class Hotel implements Serializable{

	private static final long serialVersionUID = -6271266125221828851L;

	private String name;
	
	private Location location;
	
	private String address;
	private String streetId;
	private String telePhone;
	private int detail;
	private String uId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreetId() {
		return streetId;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public int getDetail() {
		return detail;
	}
	public void setDetail(int detail) {
		this.detail = detail;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}
