package com.icc.dto;

import java.io.Serializable;

/**
 * 
 * @description 商铺详情dto
 * @author zhurun
 * @date 2016年9月29日下午3:26:46
 */
public class ShopsDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3458276781056935132L;
	
	private ShopsListDto shopsListDto;
	
	private String shopsType;//商铺类型   1-社区住宅 2-写字楼
	private String estateFee;//物业费
	private String leaseTrem;//租期
	private String shopBusinessType;//业态
	private String currentBusiness;//当前经营
	
	private String thumbUrlOne;
	private String thumbUrlTwo;
	private String thumbUrlThird;
	
	private int userClick;//浏览次数
	public ShopsListDto getShopsListDto() {
		return shopsListDto;
	}
	public void setShopsListDto(ShopsListDto shopsListDto) {
		this.shopsListDto = shopsListDto;
	}
	public String getShopsType() {
		return shopsType;
	}
	public void setShopsType(String shopsType) {
		this.shopsType = shopsType;
	}
	public String getEstateFee() {
		return estateFee;
	}
	public void setEstateFee(String estateFee) {
		this.estateFee = estateFee;
	}
	public String getLeaseTrem() {
		return leaseTrem;
	}
	public void setLeaseTrem(String leaseTrem) {
		this.leaseTrem = leaseTrem;
	}
	public String getShopBusinessType() {
		return shopBusinessType;
	}
	public void setShopBusinessType(String shopBusinessType) {
		this.shopBusinessType = shopBusinessType;
	}
	public String getCurrentBusiness() {
		return currentBusiness;
	}
	public void setCurrentBusiness(String currentBusiness) {
		this.currentBusiness = currentBusiness;
	}
	public String getThumbUrlOne() {
		return thumbUrlOne;
	}
	public void setThumbUrlOne(String thumbUrlOne) {
		this.thumbUrlOne = thumbUrlOne;
	}
	public String getThumbUrlTwo() {
		return thumbUrlTwo;
	}
	public void setThumbUrlTwo(String thumbUrlTwo) {
		this.thumbUrlTwo = thumbUrlTwo;
	}
	public String getThumbUrlThird() {
		return thumbUrlThird;
	}
	public void setThumbUrlThird(String thumbUrlThird) {
		this.thumbUrlThird = thumbUrlThird;
	}
	public int getUserClick() {
		return userClick;
	}
	public void setUserClick(int userClick) {
		this.userClick = userClick;
	}
	
	

}
