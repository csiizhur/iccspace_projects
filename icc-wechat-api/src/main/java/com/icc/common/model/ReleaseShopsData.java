package com.icc.common.model;

import java.io.Serializable;
import java.util.List;
/**
 * RequestBodyListData 商铺发布的数据
 * @description
 * @author zhurun
 * @date 2016年11月15日下午4:08:02
 */
public class ReleaseShopsData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274157450798072271L;
	/*private List<String> addDatas;//上传的照片
	public List<String> getAddDatas() {
		return addDatas;
	}
	public void setAddDatas(List<String> addDatas) {
		this.addDatas = addDatas;
	}*/
	private String addDatas;
	public String getAddDatas() {
		return addDatas;
	}
	public void setAddDatas(String addDatas) {
		this.addDatas = addDatas;
	}
	
	
	
}
