package com.icc.aop.aspect;

import java.io.Serializable;

/**
 * 
 * @description 给dto使用
 * @author zhurun
 * @date 2016年10月11日上午9:39:52
 */
public class BaseEntity implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3563933871235243678L;
	private String respCode;
	private String respDesc;
	
	//存放一些符加信息，便于返回一些有用的值
	private String[] extras;
	
	public BaseEntity(){
		
	}
	
	
	public BaseEntity(String code,String description){
		this.respCode = code;
		this.respDesc = description;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respCodeDesc) {
		this.respDesc = respCodeDesc;
	}

	public String[] getExtras() {
		return extras;
	}

	public void setExtras(String[] extras) {
		this.extras = extras;
	}
}
