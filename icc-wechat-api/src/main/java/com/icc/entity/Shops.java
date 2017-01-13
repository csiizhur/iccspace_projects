package com.icc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * @description 商铺信息基础类 
 * @author zhur
 * @date 2016年9月12日下午4:05:16
 */
public class Shops implements Serializable {


	private static final long serialVersionUID = 814248599458365328L;

	private String Id;
	private double shopSize;
	private String cityNo;
	private String areaNo;
	private String streetNo;
	private String address;
	private String estatesType;
	private int collectionFrequen;
	private int commentsFrequen;
	private int forwardFrequen;
	private int isShow;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int deleted;
	private String addressUnique;
	private String historyId;
	
	private String cityName;
	private String areaName;
	private String streetName;
	
	private String lat;//纬度
	private String lng;//经度
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public double getShopSize() {
		return shopSize;
	}
	public void setShopSize(double shopSize) {
		this.shopSize = shopSize;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEstatesType() {
		return estatesType;
	}
	public void setEstatesType(String estatesType) {
		this.estatesType = estatesType;
	}
	public int getCollectionFrequen() {
		return collectionFrequen;
	}
	public void setCollectionFrequen(int collectionFrequen) {
		this.collectionFrequen = collectionFrequen;
	}
	public int getCommentsFrequen() {
		return commentsFrequen;
	}
	public void setCommentsFrequen(int commentsFrequen) {
		this.commentsFrequen = commentsFrequen;
	}
	public int getForwardFrequen() {
		return forwardFrequen;
	}
	public void setForwardFrequen(int forwardFrequen) {
		this.forwardFrequen = forwardFrequen;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getAddressUnique() {
		return addressUnique;
	}
	public void setAddressUnique(String addressUnique) {
		this.addressUnique = addressUnique;
	}
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
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
