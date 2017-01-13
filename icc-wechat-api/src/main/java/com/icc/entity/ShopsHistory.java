package com.icc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @description 商铺更新数据信息类
 * @author zhur
 * @date 2016年9月12日下午4:10:35
 */
public class ShopsHistory implements Serializable {

	private static final long serialVersionUID = -8150527282273959001L;

	private String id;
	private String baseShopsId;
	private String shopsName;
	private String shopsBrand;
	private int rentType;
	private BigDecimal rentFee;
	private BigDecimal totalRentFee;
	private String rentTrem;//租赁时间/年
	private String expectBusinessType;
	private BigDecimal estateFee;
	private String estatesType;//物业类型
	private Timestamp releaseTime;
	private String userId;//发布用户编号
	private int releaseStatus;
	private String thumbnailURL;
	private Timestamp createTime;
	private String shopsUnique;
	private int releaseType;//发布类型
	private int userClick;
	
	private String currentShopStatus;//当前店铺状态
	private BigDecimal expectRentFee;//期望租金
	private String expectRentFeeMin;
	private String expectRentFeeMax;
	private String expectShopSizeMin;
	private String expectShopSizeMax;
	//private String expectBusinessType;//期望业态
	private String rentBrand;//租户品牌
	private Timestamp rentTime;
	private int floor;//楼层
	private String openRoom;//开间
	private double stories;//层高
	private String columnsBetween;//柱间
	private double depth;//进深
	private String currentShopBusinessType;//当前店铺业态
	private String surplusLeaseTrem;//剩余租期
	private String contractEndDate;//原合同截至日
	private BigDecimal contractRentFee;//原合同租金
	private String shareRentModel;//合租方式
	private BigDecimal splitRentFee;//合租需分摊租金
	private String mobilePhone;
	//private int rentType;//出租类型
	
	private String renovation;//装修
	private String parkingLot;//车位
	private String tenantProductRequire;//对租户的产品要求
	private String subletBrand;//你的品牌
	private String subletMode;//转租方式
	private BigDecimal subletFee;//转租费
	private String subletReason;//转租原因
	
	private int version;
	
	private List<ShopsPhotosInfo> photos;
	
	private String businessType;//经营业态
	private String expectShopSize;//期望面积
	
	private String equipmentList;//设备清单
	private Integer weekOrMonthType;//1：按月2：按周
	private String weekOrMonthData;//按月 按周
	private String rentVailableSize;//可供合租面积
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBaseShopsId() {
		return baseShopsId;
	}
	public void setBaseShopsId(String baseShopsId) {
		this.baseShopsId = baseShopsId;
	}
	public String getShopsName() {
		return shopsName;
	}
	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}
	public String getShopsBrand() {
		return shopsBrand;
	}
	public void setShopsBrand(String shopsBrand) {
		this.shopsBrand = shopsBrand;
	}
	public int getRentType() {
		return rentType;
	}
	public void setRentType(int rentType) {
		this.rentType = rentType;
	}
	public BigDecimal getRentFee() {
		return rentFee;
	}
	public void setRentFee(BigDecimal rentFee) {
		this.rentFee = rentFee;
	}
	public BigDecimal getTotalRentFee() {
		return totalRentFee;
	}
	public void setTotalRentFee(BigDecimal totalRentFee) {
		this.totalRentFee = totalRentFee;
	}
	public String getRentTrem() {
		return rentTrem;
	}
	public void setRentTrem(String rentTrem) {
		this.rentTrem = rentTrem;
	}
	public String getExpectBusinessType() {
		return expectBusinessType;
	}
	public void setExpectBusinessType(String expectBusinessType) {
		this.expectBusinessType = expectBusinessType;
	}
	public BigDecimal getEstateFee() {
		return estateFee;
	}
	public void setEstateFee(BigDecimal estateFee) {
		this.estateFee = estateFee;
	}
	public String getEstatesType() {
		return estatesType;
	}
	public void setEstatesType(String estatesType) {
		this.estatesType = estatesType;
	}
	public Timestamp getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(int releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public String getThumbnailURL() {
		return thumbnailURL;
	}
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getShopsUnique() {
		return shopsUnique;
	}
	public void setShopsUnique(String shopsUnique) {
		this.shopsUnique = shopsUnique;
	}
	public int getReleaseType() {
		return releaseType;
	}
	public void setReleaseType(int releaseType) {
		this.releaseType = releaseType;
	}
	public String getCurrentShopStatus() {
		return currentShopStatus;
	}
	public void setCurrentShopStatus(String currentShopStatus) {
		this.currentShopStatus = currentShopStatus;
	}
	public BigDecimal getExpectRentFee() {
		return expectRentFee;
	}
	public void setExpectRentFee(BigDecimal expectRentFee) {
		this.expectRentFee = expectRentFee;
	}
	public String getRentBrand() {
		return rentBrand;
	}
	public void setRentBrand(String rentBrand) {
		this.rentBrand = rentBrand;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getOpenRoom() {
		return openRoom;
	}
	public void setOpenRoom(String openRoom) {
		this.openRoom = openRoom;
	}
	public double getStories() {
		return stories;
	}
	public void setStories(double stories) {
		this.stories = stories;
	}
	public String getColumnsBetween() {
		return columnsBetween;
	}
	public void setColumnsBetween(String columnsBetween) {
		this.columnsBetween = columnsBetween;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public String getCurrentShopBusinessType() {
		return currentShopBusinessType;
	}
	public void setCurrentShopBusinessType(String currentShopBusinessType) {
		this.currentShopBusinessType = currentShopBusinessType;
	}
	public String getSurplusLeaseTrem() {
		return surplusLeaseTrem;
	}
	public void setSurplusLeaseTrem(String surplusLeaseTrem) {
		this.surplusLeaseTrem = surplusLeaseTrem;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public BigDecimal getContractRentFee() {
		return contractRentFee;
	}
	public void setContractRentFee(BigDecimal contractRentFee) {
		this.contractRentFee = contractRentFee;
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
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Timestamp getRentTime() {
		return rentTime;
	}
	public void setRentTime(Timestamp rentTime) {
		this.rentTime = rentTime;
	}
	public int getUserClick() {
		return userClick;
	}
	public void setUserClick(int userClick) {
		this.userClick = userClick;
	}
	public List<ShopsPhotosInfo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<ShopsPhotosInfo> photos) {
		this.photos = photos;
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
	public String getTenantProductRequire() {
		return tenantProductRequire;
	}
	public void setTenantProductRequire(String tenantProductRequire) {
		this.tenantProductRequire = tenantProductRequire;
	}
	public String getSubletBrand() {
		return subletBrand;
	}
	public void setSubletBrand(String subletBrand) {
		this.subletBrand = subletBrand;
	}
	public String getSubletMode() {
		return subletMode;
	}
	public void setSubletMode(String subletMode) {
		this.subletMode = subletMode;
	}
	public BigDecimal getSubletFee() {
		return subletFee;
	}
	public void setSubletFee(BigDecimal subletFee) {
		this.subletFee = subletFee;
	}
	public String getSubletReason() {
		return subletReason;
	}
	public void setSubletReason(String subletReason) {
		this.subletReason = subletReason;
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
	public String getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(String equipmentList) {
		this.equipmentList = equipmentList;
	}
	public Integer getWeekOrMonthType() {
		return weekOrMonthType;
	}
	public void setWeekOrMonthType(Integer weekOrMonthType) {
		this.weekOrMonthType = weekOrMonthType;
	}
	public String getWeekOrMonthData() {
		return weekOrMonthData;
	}
	public void setWeekOrMonthData(String weekOrMonthData) {
		this.weekOrMonthData = weekOrMonthData;
	}
	public String getRentVailableSize() {
		return rentVailableSize;
	}
	public void setRentVailableSize(String rentVailableSize) {
		this.rentVailableSize = rentVailableSize;
	}
	
	
}
