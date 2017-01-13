package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 周边配套
 * @description
 * @author zhurun
 * @date 2016年12月1日下午4:55:20
 */
public class AssortPlaceResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1563861431013529075L;
	private List<Map<String,Object>> trafficList;
	private List<Map<String,Object>> healthList;
	private List<Map<String,Object>> educationList;
	private List<Map<String,Object>> govList;
	private List<Map<String,Object>> bankList;
	public List<Map<String, Object>> getTrafficList() {
		return trafficList;
	}
	public void setTrafficList(List<Map<String, Object>> trafficList) {
		this.trafficList = trafficList;
	}
	public List<Map<String, Object>> getHealthList() {
		return healthList;
	}
	public void setHealthList(List<Map<String, Object>> healthList) {
		this.healthList = healthList;
	}
	public List<Map<String, Object>> getEducationList() {
		return educationList;
	}
	public void setEducationList(List<Map<String, Object>> educationList) {
		this.educationList = educationList;
	}
	public List<Map<String, Object>> getGovList() {
		return govList;
	}
	public void setGovList(List<Map<String, Object>> govList) {
		this.govList = govList;
	}
	public List<Map<String, Object>> getBankList() {
		return bankList;
	}
	public void setBankList(List<Map<String, Object>> bankList) {
		this.bankList = bankList;
	}

	
	
}
