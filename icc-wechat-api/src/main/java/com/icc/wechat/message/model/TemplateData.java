package com.icc.wechat.message.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 模版消息 POST数据的data对象
 * @description
 * @author zhurun
 * @date 2016年11月10日下午12:59:00
 */

import com.alibaba.fastjson.JSONObject;
public class TemplateData implements Serializable {


	private static final long serialVersionUID = 5454898758328334735L;

	private Map<String,String> first;
	private Map keynote1;
	private Map keynote2;
	private Map keynote3;
	private Map remark;
	public Map<String,String> getFirst() {
		return first;
	}
	public void setFirst(Map<String,String> first) {
		this.first = first;
	}
	public Map getKeynote1() {
		return keynote1;
	}
	public void setKeynote1(Map keynote1) {
		this.keynote1 = keynote1;
	}
	public Map getKeynote2() {
		return keynote2;
	}
	public void setKeynote2(Map keynote2) {
		this.keynote2 = keynote2;
	}
	public Map getKeynote3() {
		return keynote3;
	}
	public void setKeynote3(Map keynote3) {
		this.keynote3 = keynote3;
	}
	public Map getRemark() {
		return remark;
	}
	public void setRemark(Map remark) {
		this.remark = remark;
	}
	
}
