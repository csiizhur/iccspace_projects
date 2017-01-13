package com.icc.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商铺修改的数据
 * @description
 * @author zhurun
 * @date 2016年11月15日下午4:11:36
 */
public class UpdateShopsData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1280412313221429137L;

	/*private List<String> addDatas;
	private List<String> delIds;
	public List<String> getAddDatas() {
		return addDatas;
	}
	public void setAddDatas(List<String> addDatas) {
		this.addDatas = addDatas;
	}
	public List<String> getDelIds() {
		return delIds;
	}
	public void setDelIds(List<String> delIds) {
		this.delIds = delIds;
	}*/
	
	private String addDatas;
	private String delIds;
	public String getAddDatas() {
		return addDatas;
	}
	public void setAddDatas(String addDatas) {
		this.addDatas = addDatas;
	}
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	
	
	
}
