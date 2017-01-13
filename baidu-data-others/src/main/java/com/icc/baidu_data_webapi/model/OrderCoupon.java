package com.icc.baidu_data_webapi.model;

import java.io.Serializable;

/**
 * t_order_coupon
 * @author Jian
 *
 */
public class OrderCoupon implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 订单号
	 */
	private String orderSn;
	/**
	 * 券code
	 */
	private String code;
	/**
	 * 券快照号
	 */
	private String snapCode;
	/**
	 * 类型 1优惠券 2 消费券 3 电子会员卡
	 */
	private Integer type;
	/**
	 * 类型 1通用券 2 专用券
	 */
	private Integer direction; 
	/**
	 * name
	 */
	private String name;
	/**
	 * 原价
	 */
	private Integer costPrice;
	/**
	 * 出售价格
	 */
	private Integer saledPrice;
	/**
	 * 票面价值
	 */
	private Integer parPrice;
	/**
	 * 结束时间
	 */
	private java.util.Date endTime;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * quantity
	 */
	private Integer quantity;
	/**
	 * buyPrice
	 */
	private Integer buyPrice;
	
	/**
	 * 商家code
	 */
	private String merchantCode;

	/**
	 * 用户code
	 */
	private String memberCode;
	/**
	 * 发送手机号码
	 */
	private String mobile;

	/**
	 * logo图片
	 */
	private String logoImg;

	/**
	 * 图片
	 */
	private String img;
	

	private String fromShopCode;
	private String fromShopName;
	private String fromShopAddress;
	private String summry;
	private String fromShopTel;

	/**
	 * 可用次数 针对按次消费的券
	 */
	private int totalTimes;

	private String content;

	private String memberName;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSnapCode() {
		return snapCode;
	}

	public void setSnapCode(String snapCode) {
		this.snapCode = snapCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Integer costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getSaledPrice() {
		return saledPrice;
	}

	public void setSaledPrice(Integer saledPrice) {
		this.saledPrice = saledPrice;
	}

	public Integer getParPrice() {
		return parPrice;
	}

	public void setParPrice(Integer parPrice) {
		this.parPrice = parPrice;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFromShopCode() {
		return fromShopCode;
	}

	public void setFromShopCode(String fromShopCode) {
		this.fromShopCode = fromShopCode;
	}

	public String getFromShopName() {
		return fromShopName;
	}

	public void setFromShopName(String fromShopName) {
		this.fromShopName = fromShopName;
	}

	public String getFromShopAddress() {
		return fromShopAddress;
	}

	public void setFromShopAddress(String fromShopAddress) {
		this.fromShopAddress = fromShopAddress;
	}

	public String getSummry() {
		return summry;
	}

	public void setSummry(String summry) {
		this.summry = summry;
	}

	public String getFromShopTel() {
		return fromShopTel;
	}

	public void setFromShopTel(String fromShopTel) {
		this.fromShopTel = fromShopTel;
	}

	public int getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	
	
}
