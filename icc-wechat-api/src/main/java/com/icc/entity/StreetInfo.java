package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 街道信息
 * @author zhur
 * @date 2016年9月13日上午9:38:54
 */
public class StreetInfo implements Serializable {

	private static final long serialVersionUID = -5171072969551858911L;

	private String Id;
	private String streetName;
	private String streetNo;
	private int streetStatus;
	private String belongAreaNo;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public int getStreetStatus() {
		return streetStatus;
	}
	public void setStreetStatus(int streetStatus) {
		this.streetStatus = streetStatus;
	}
	public String getBelongAreaNo() {
		return belongAreaNo;
	}
	public void setBelongAreaNo(String belongAreaNo) {
		this.belongAreaNo = belongAreaNo;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
}
