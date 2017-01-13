package com.icc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @description
 * @author zhurun
 * @date 2016年10月24日下午12:45:13
 */
public class RentShops implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1964588421309602696L;

	private String rentId;
	private String cityNo;
	private String areaNo;
	private String streetNo;
	private String address;
	private String businessType; // '经营业态',
	private String expectShopSize; // '期望面积',
	private BigDecimal expectRentFee;//期望租金(m/月)
	private String expectRentFeeMin;//期望租金(m/月)
	private String expectRentFeeMax;//期望租金(m/月)
	private String expectShopSizeMin;//期望租金(m/月)
	private String expectShopSizeMax;//期望租金(m/月)
	private String rentTrem; // '租赁时间(年)',
	private String estatesType; // '物业类型',
	private double stories;//层高
	private String renovation; // '装修',
	private String parkingLot; // '车位',
	private String subletBrand; // '你的品牌',
	private String shareRentModel; // '合租方式',
	private BigDecimal splitRentFee; // '合租需分摊租金',
	private String userId; // '发布人',
	private int COLLECTION_FREQUEN;
	private int FORWARD_FREQUEN;
	private int COMMENTS_FREQUEN;
	private String ThumbnailUrl;
	private int releaseType;
	private String mobilePhone;
	public String getRentId() {
		return rentId;
	}
	public void setRentId(String rentId) {
		this.rentId = rentId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getExpectShopSize() {
		return expectShopSize;
	}
	public void setExpectShopSize(String expectShopSize) {
		this.expectShopSize = expectShopSize;
	}
	public String getRentTrem() {
		return rentTrem;
	}
	public void setRentTrem(String rentTrem) {
		this.rentTrem = rentTrem;
	}
	public String getEstatesType() {
		return estatesType;
	}
	public void setEstatesType(String estatesType) {
		this.estatesType = estatesType;
	}
	public String getRenovation() {
		return renovation;
	}
	public void setRenovation(String renovation) {
		this.renovation = renovation;
	}
	public String getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}
	public String getSubletBrand() {
		return subletBrand;
	}
	public void setSubletBrand(String subletBrand) {
		this.subletBrand = subletBrand;
	}
	public String getShareRentModel() {
		return shareRentModel;
	}
	public void setShareRentModel(String shareRentModel) {
		this.shareRentModel = shareRentModel;
	}
	public BigDecimal getSplitRentFee() {
		return splitRentFee;
	}
	public void setSplitRentFee(BigDecimal splitRentFee) {
		this.splitRentFee = splitRentFee;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCOLLECTION_FREQUEN() {
		return COLLECTION_FREQUEN;
	}
	public void setCOLLECTION_FREQUEN(int cOLLECTION_FREQUEN) {
		COLLECTION_FREQUEN = cOLLECTION_FREQUEN;
	}
	public int getFORWARD_FREQUEN() {
		return FORWARD_FREQUEN;
	}
	public void setFORWARD_FREQUEN(int fORWARD_FREQUEN) {
		FORWARD_FREQUEN = fORWARD_FREQUEN;
	}
	public int getCOMMENTS_FREQUEN() {
		return COMMENTS_FREQUEN;
	}
	public void setCOMMENTS_FREQUEN(int cOMMENTS_FREQUEN) {
		COMMENTS_FREQUEN = cOMMENTS_FREQUEN;
	}
	public BigDecimal getExpectRentFee() {
		return expectRentFee;
	}
	public void setExpectRentFee(BigDecimal expectRentFee) {
		this.expectRentFee = expectRentFee;
	}
	public double getStories() {
		return stories;
	}
	public void setStories(double stories) {
		this.stories = stories;
	}
	public String getThumbnailUrl() {
		return ThumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		ThumbnailUrl = thumbnailUrl;
	}
	public int getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(int releaseType) {
		this.releaseType = releaseType;
	}
	public String getExpectRentFeeMin() {
		return expectRentFeeMin;
	}
	public void setExpectRentFeeMin(String expectRentFeeMin) {
		this.expectRentFeeMin = expectRentFeeMin;
	}
	public String getExpectRentFeeMax() {
		return expectRentFeeMax;
	}
	public void setExpectRentFeeMax(String expectRentFeeMax) {
		this.expectRentFeeMax = expectRentFeeMax;
	}
	public String getExpectShopSizeMin() {
		return expectShopSizeMin;
	}
	public void setExpectShopSizeMin(String expectShopSizeMin) {
		this.expectShopSizeMin = expectShopSizeMin;
	}
	public String getExpectShopSizeMax() {
		return expectShopSizeMax;
	}
	public void setExpectShopSizeMax(String expectShopSizeMax) {
		this.expectShopSizeMax = expectShopSizeMax;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
}
