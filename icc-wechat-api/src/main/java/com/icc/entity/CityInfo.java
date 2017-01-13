package com.icc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @description 城市信息 
 * @author zhur
 * @date 2016年9月13日上午9:09:11
 */
public class CityInfo implements Serializable {

	private static final long serialVersionUID = -3643253718787448049L;

	private String Id;
	private String cityName;
	private String cityNo;
	private int cityStatus;
	private int deleted;
	private List<AreaInfo> area;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public int getCityStatus() {
		return cityStatus;
	}
	public void setCityStatus(int cityStatus) {
		this.cityStatus = cityStatus;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public List<AreaInfo> getArea() {
		return area;
	}
	public void setArea(List<AreaInfo> area) {
		this.area = area;
	}

}
