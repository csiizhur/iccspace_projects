package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 周边竞争
 * @description
 * @author zhurun
 * @date 2016年12月1日下午4:55:01
 */
public class CompetePlaceResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1016192572274167068L;
	private List<Map<String,Object>> caterList;
	private List<Map<String,Object>> salonList;
	private List<Map<String,Object>> supermarketList;
	private List<Map<String,Object>> hotelList;
	
	
	
	public List<Map<String, Object>> getCaterList() {
		return caterList;
	}
	public void setCaterList(List<Map<String, Object>> caterList) {
		this.caterList = caterList;
	}
	public List<Map<String, Object>> getSalonList() {
		return salonList;
	}
	public void setSalonList(List<Map<String, Object>> salonList) {
		this.salonList = salonList;
	}
	public List<Map<String, Object>> getSupermarketList() {
		return supermarketList;
	}
	public void setSupermarketList(List<Map<String, Object>> supermarketList) {
		this.supermarketList = supermarketList;
	}
	public List<Map<String, Object>> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<Map<String, Object>> hotelList) {
		this.hotelList = hotelList;
	}
	
}
