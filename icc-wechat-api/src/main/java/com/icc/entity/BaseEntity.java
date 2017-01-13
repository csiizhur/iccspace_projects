package com.icc.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @description 返回数据基类--给controller返回数据用
 * @author zhur
 * @date 2016年9月8日下午2:56:36
 */
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<String,Object> head=new HashMap<String,Object>();
	
	private Object body=new Object();

	public Map<String, Object> getHead() {
		return head;
	}

	public void setHead(Map<String, Object> head) {
		this.head = head;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	
}
