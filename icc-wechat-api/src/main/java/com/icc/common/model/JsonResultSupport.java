package com.icc.common.model;

import java.io.Serializable;

/**
 * 实现JsonResult 返回head和body
 * @description
 * @author zhurun
 * @date 2016年10月20日下午5:47:29
 * @param <T>
 */
public class JsonResultSupport<T> implements JsonResult<T>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9011289919300415520L;

	private T head;
	private Object body;

	@Override
	public void setBody(Object body) {
		this.body=body;
		
	}
	@Override
	public Object getBody() {
		return body;
	}
	@Override
	public void setHead(T head) {
		this.head=head;
		
	}
	@Override
	public T getHead() {
		return head;
	}
	

}
