package com.icc.common.model.list;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 过滤dto
 * @description
 * @author zhurun
 * @date 2016年10月26日下午4:07:42
 */
public class FilterRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1669020061934340858L;
	
	private List<String> areaNos;//区域
	
	private List<String> estatesTypes;//物业类型
	
	private double minSize;
	private double maxSize;//面积
	
	private BigDecimal minRentFee;
	private BigDecimal maxRentFee;//租金
	public List<String> getAreaNos() {
		return areaNos;
	}
	public void setAreaNos(List<String> areaNos) {
		this.areaNos = areaNos;
	}
	public List<String> getEstatesTypes() {
		return estatesTypes;
	}
	public void setEstatesTypes(List<String> estatesTypes) {
		this.estatesTypes = estatesTypes;
	}
	public double getMinSize() {
		return minSize;
	}
	public void setMinSize(double minSize) {
		this.minSize = minSize;
	}
	public double getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(double maxSize) {
		this.maxSize = maxSize;
	}
	public BigDecimal getMinRentFee() {
		return minRentFee;
	}
	public void setMinRentFee(BigDecimal minRentFee) {
		this.minRentFee = minRentFee;
	}
	public BigDecimal getMaxRentFee() {
		return maxRentFee;
	}
	public void setMaxRentFee(BigDecimal maxRentFee) {
		this.maxRentFee = maxRentFee;
	}
	
	
}
