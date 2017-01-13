package com.icc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.list.FilterRequestDto;
import com.icc.entity.Shops;
import com.icc.entity.ShopsHistory;

public interface ShopsService {

	//新铺(出租)
	PageInfo<Map<Object,Object>> getNewShopsList(Integer pageNo,Integer pageSize);
	PageInfo<Map<Object,Object>> getNewShopsList(Integer pageNo,Integer pageSize,List<String> listAreaNo,List<String> estatesTypes,Double minSize,Double maxSize,BigDecimal minRentFee,BigDecimal maxRentFee);
	//新铺(求租)
	PageInfo<Map<Object,Object>> getNewRentShopsList(Integer pageNo,List<String> listAreaNo,String minExpectRentFee,String maxExpectRentFee,String minExpectSize,String maxExpectSize,List<String> estatesType);
	//推荐(出租)
	Map<String,Object> getRecommendShopsList(String userId,Integer pageNo,Integer pageSize);
	//推荐(求租)
	PageInfo<Map<Object,Object>> getRecommendRentShopsList(String userId,Integer pageNo);
	//热搜(出租)	
	public PageInfo<Map<String,Object>> getHotSearchShopsList(String userId,Integer pageNum,Integer pageSize,FilterRequestDto dto);
	//热搜(求租)
	public PageInfo<Map<String,Object>> getHotSearchRentShopsList(Integer pageNum,List<String> areaNos,String minExpectRentFee,String maxExpectRentFee,String minExpectSize,String maxExpectSize,List<String>estatesType);
	//搜索(出租)
	PageInfo<Map<Object,Object>> getShopsListBySearch(Map m,Integer pageNum,Integer pageSize);

	Map<String,Object> queryBaseShopsInfoUnique(int addressUnique);
	int insertBaseShopInformationRelease(Shops s);
	
	int insertHistoryShopInformationRelease(ShopsHistory sh);
	
	int updateBaseShopInformationRelease(Map<String,Object> map);
	
	//我的发布商铺
	PageInfo<Map<String,Object>> queryShopInformationByUserId(String userId,Integer pageNum,Integer pageSize);
	//删除我的发布
	HeadMessageDto deleteReleaseShopsByShopsIdForUser(String userId,String shopsId);
	//商铺详情(出租)
	Map<String,Object> queryShopsInfoById(String shopsId,String currentUserId);
	
	//商铺发布
	public boolean releaseShopsInformationAddOrUpdate(Shops shop,ShopsHistory sh,String historyShopId,String baseShopId);
	
	//新 商铺发布(求租+出租)
	public HeadMessageDto releaseShopsInformation(Shops s,ShopsHistory sh,String historyShopId,String baseShopId,String rentShopId,List<String> data,String userId);
	public HeadMessageDto releaseShopsInformationByWeixin(Shops s,ShopsHistory sh,String historyShopId,String baseShopId,String rentShopId,List<String> data,String userId);
}
