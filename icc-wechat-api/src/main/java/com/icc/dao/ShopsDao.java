package com.icc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.icc.dto.UserExpectShopsDto;
import com.icc.entity.Shops;
import com.icc.entity.ShopsHistory;

/**
 * 
 * @description 商铺接口
 * @author zhur
 * @date 2016年9月13日上午11:20:13
 */
public interface ShopsDao {

	//新铺列表
	List<Map<Object,Object>> queryNewShopsList();
	List<Map<Object,Object>> queryNewShopsList(@Param("listAreaNo")List<String> listAreaNo,@Param("estatesTypes")List<String>estatesTypes,@Param("shopMinSize") Double shopMinSize,@Param("shopMaxSize")Double shopMaxSize,@Param("minRentFee")BigDecimal minRentFee,@Param("maxRentFee")BigDecimal maxRentFee);
	//推荐商铺
	List<Map<Object,Object>> queryRecommendShopsList();
	//商铺搜索
	List<Map<Object,Object>> queryShopsListBySearch(Map m);
	
	//商铺发布
	Map<String,Object> queryBaseShopsInfoUnique(int addressUnique);
	String queryBaseShopsInfoUnique(String addressUnique);
	int insertBaseRentShopInformationRelease(Shops s);
	int insertRentHistoryShopInformationRelease(ShopsHistory sh);
	int updateBaseRentShopInformationRelease(Map<String,Object> map);
	//@Param("historyId")String historyId,@Param("addressUnique")String addressUnique,@Param("baseShopsId")String baseShopsId,Map<String,String> params
	//int updateBaseRentShopInformationRelease(@Param("historyId")String historyId,@Param("addressUnique")String addressUnique,@Param("baseShopsId")String baseShopsId,Map<String,String> params);
	
	//我的发布商铺
	List<Map<String,Object>> queryShopInformationByUserId(String userId);
	//删除我的商铺
	int deleteReleaseShopsByShopsIdForUser(@Param(value="userId")String userId,@Param("shopsId") String shopsId);
	
	//商铺详情--历史表的id
	Map<String,Object> queryShopsInfoById(String shopsId);
	
	//推荐商铺(多字段打分)
	Page<Map<Object,Object>> queryRecommendShopsBySort(UserExpectShopsDto map);
	//使用or
	Page<Map<Object,Object>> queryRecommendShopsBySortNoBusiness(UserExpectShopsDto map);
	
	//记录用户点击商铺次数    --- 多个参数非map传值使用@Param注解
	public int updateShopsUserClick(@Param("userClick")int userClick,@Param("shopsId") String shopsId);
	
	//热搜商铺
	public List<Map<String,Object>> queryHotSearchShopsList(@Param("array") int[] releaseType,@Param("areaNoList")List<String> areaNoList,@Param("estatesTypeList")List<String> estatesTypeList,@Param("minSize") double minSize,@Param("maxSize")double maxSize,@Param("minRentFee")BigDecimal minRentFee,@Param("maxRentFee")BigDecimal maxRentFee);
}
