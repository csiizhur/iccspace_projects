package com.icc.baidu_data_webapi.model.hotel;

import java.io.Serializable;
import java.util.List;

/**
 * json result
 * @description
 * @author zhurun
 * @date 2016年10月19日下午1:39:12
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 2763741504861754185L;
	private String message;
	private int status;
	private List<?> results;
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
	public List<?> getResults() {
		return results;
	}
	public void setResults(List<?> results) {
		this.results = results;
	}
}
