package com.icc.common.model;

public interface JsonResult<T> {

	public void setHead(T head);
	public T getHead();
	public void setBody(Object body);
	public Object getBody();
}
