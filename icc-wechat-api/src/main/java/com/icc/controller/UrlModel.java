package com.icc.controller;

import java.io.Serializable;
/**
 * @RequestBody json方式请求数据绑定
 * @description
 * @author zhurun
 * @date 2016年12月2日下午4:34:10
 */
public class UrlModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3479657601442525738L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
