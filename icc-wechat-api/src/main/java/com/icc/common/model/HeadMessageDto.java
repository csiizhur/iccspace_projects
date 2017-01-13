package com.icc.common.model;

import java.io.Serializable;
/**
 * 记录service返回消息
 * @description
 * @author zhurun
 * @date 2016年10月20日下午2:19:58
 */
public class HeadMessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8904746032774549605L;

	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
