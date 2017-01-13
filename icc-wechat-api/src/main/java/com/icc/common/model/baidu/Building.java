package com.icc.common.model.baidu;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.icc.common.model.Location;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Building implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3290820524885638255L;
	private String name;
	private Location location;
	private String address;
	private String telephone;
	private String street_id;
	private Integer detail;
	private String uid;
	private DetailInfo detail_info;
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
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}
	public Integer getDetail() {
		return detail;
	}
	public void setDetail(Integer detail) {
		this.detail = detail;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public DetailInfo getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(DetailInfo detail_info) {
		this.detail_info = detail_info;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
