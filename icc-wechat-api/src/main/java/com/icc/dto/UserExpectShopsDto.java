package com.icc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @description 用户期望
 * @author zhurun
 * @date 2016年9月30日下午12:23:10
 */
public class UserExpectShopsDto implements Serializable {

	
	private static final long serialVersionUID = -2389211568458224959L;

	private String userId;
	private String releaseShopsId;//发布的ID
	private String city;
	private String area;
	private String street;
	private String address;
	private double shopSize;
	private String businessType;
	private BigDecimal rentFee;
	private String expectShopSizeMin;
	private String expectShopSizeMax;
	private String expectRentFeeMin;
	private String expectRentFeeMax;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReleaseShopsId() {
		return releaseShopsId;
	}
	public void setReleaseShopsId(String releaseShopsId) {
		this.releaseShopsId = releaseShopsId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getShopSize() {
		return shopSize;
	}
	public void setShopSize(double shopSize) {
		this.shopSize = shopSize;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public BigDecimal getRentFee() {
		return rentFee;
	}
	public void setRentFee(BigDecimal rentFee) {
		this.rentFee = rentFee;
	}
	public String getExpectShopSizeMin() {
		return expectShopSizeMin;
	}
	public void setExpectShopSizeMin(String expectShopSizeMin) {
		this.expectShopSizeMin = expectShopSizeMin;
	}
	public String getExpectShopSizeMax() {
		return expectShopSizeMax;
	}
	public void setExpectShopSizeMax(String expectShopSizeMax) {
		this.expectShopSizeMax = expectShopSizeMax;
	}
	public String getExpectRentFeeMin() {
		return expectRentFeeMin;
	}
	public void setExpectRentFeeMin(String expectRentFeeMin) {
		this.expectRentFeeMin = expectRentFeeMin;
	}
	public String getExpectRentFeeMax() {
		return expectRentFeeMax;
	}
	public void setExpectRentFeeMax(String expectRentFeeMax) {
		this.expectRentFeeMax = expectRentFeeMax;
	}
	
	
}
