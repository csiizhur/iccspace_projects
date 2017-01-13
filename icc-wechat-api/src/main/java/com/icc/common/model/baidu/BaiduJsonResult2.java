package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * json result
 * @description
 * @author zhurun
 * @date 2016年10月19日下午1:39:12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaiduJsonResult2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763741504861754185L;
	private String message;
	private int status;
	private List<Building> results;
	private int total;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Building> getResults() {
		return results;
	}
	public void setResults(List<Building> results) {
		this.results = results;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
