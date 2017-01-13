package com.icc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @description 区域信息类 
 * @author zhur
 * @date 2016年9月13日上午9:03:32
 */
public class AreaInfo implements Serializable {

	private static final long serialVersionUID = 5334408026429718172L;

	private String Id;
	private String areaName;
	private String areaNo;
	private int areaStatus;
	private String belongCityNo;
	private int deleted;
	
	private List<StreetInfo> street;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public int getAreaStatus() {
		return areaStatus;
	}
	public void setAreaStatus(int areaStatus) {
		this.areaStatus = areaStatus;
	}
	public String getBelongCityNo() {
		return belongCityNo;
	}
	public void setBelongCityNo(String belongCityNo) {
		this.belongCityNo = belongCityNo;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public List<StreetInfo> getStreet() {
		return street;
	}
	public void setStreet(List<StreetInfo> street) {
		this.street = street;
	}
	
	
}
