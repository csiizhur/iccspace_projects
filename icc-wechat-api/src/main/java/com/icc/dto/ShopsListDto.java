package com.icc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @description 列表显示信息
 * @author zhurun
 * @date 2016年9月29日下午3:22:22
 */
public class ShopsListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7256547424893517393L;

	private String baseShopsId;
	private String historyShopsId;
	private int releaseType;//发布类型
	private BigDecimal rentFee;//元/平米/月
	private BigDecimal totalRentFee;
	private double sizes;//面积
	private String address;
	private String estateType;//物业类型
	
	
	public int getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(int releaseType) {
		this.releaseType = releaseType;
	}
	public BigDecimal getRentFee() {
		return rentFee;
	}
	public void setRentFee(BigDecimal rentFee) {
		this.rentFee = rentFee;
	}
	public BigDecimal getTotalRentFee() {
		return totalRentFee;
	}
	public void setTotalRentFee(BigDecimal totalRentFee) {
		this.totalRentFee = totalRentFee;
	}
	
	public double getSizes() {
		return sizes;
	}
	public void setSizes(double sizes) {
		this.sizes = sizes;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	public String getBaseShopsId() {
		return baseShopsId;
	}
	public void setBaseShopsId(String baseShopsId) {
		this.baseShopsId = baseShopsId;
	}
	public String getHistoryShopsId() {
		return historyShopsId;
	}
	public void setHistoryShopsId(String historyShopsId) {
		this.historyShopsId = historyShopsId;
	}
	
	
}
