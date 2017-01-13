package com.icc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.icc.dto.UserExpectShopsDto;
import com.icc.entity.RentShops;

public interface RentShopsDao {

	/**
     * 需要使用乐观锁更新使用此方法
     * @param t
     * @return
     */
    public int updateForOptimisticLocking(@Param("userClick")int userClick,@Param("rentId")String shopsId,@Param("version")int version);
	//新增 求租
	public int insertRentShopsInfo(Map<String,Object> map);
	public int insertRentShopsInfoExistId(Map<String,Object> map);
	//租户详情
	public Map<String,Object> queryRentShopsInfoById(String rentId);
	//新铺(求租)
	public List<Map<Object,Object>> queryNewRentShopsList(@Param("listAreaNo")List<String> listAreaNo,@Param("minExpectRentFee")String minExpectRentFee,@Param("maxExpectRentFee")String maxExpectRentFee,@Param("minExpectSize")String minExpectSize,@Param("maxExpectSize")String maxExpectSize,@Param("estatesType")List<String> estatesType);
	//热搜(求租)
	public List<Map<String,Object>> queryHotSearchRentShopsList(@Param("releaseType") int releaseType,@Param("areaNos")List<String> areaNos,@Param("minExpectRentFee")String minExpectRentFee,@Param("maxExpectRentFee")String maxExpectRentFee,@Param("minExpectSize")String minExpectSize,@Param("maxExpectSize")String maxExpectSize,@Param("estatesType")List<String>estatesType);
	//推荐(求租)
	public Page<Map<Object,Object>> queryRecommendRentShopsBySort(UserExpectShopsDto map);
	//or
	public Page<Map<Object,Object>> queryRecommendRentShopsBySortNoBusiness(UserExpectShopsDto map);
	//推荐(求租-all)
	public List<Map<Object,Object>> queryRecommendRentShopsList();
	//编辑发布商铺(求租)
    public int updateRentShopInformationRelease(RentShops rs);
    //我的商铺
  	public List<Map<String,Object>> queryRentShopInformationByUserId(String userId);
  	//删除我的商铺
  	public int deleteReleaseRentShopsByShopsIdForUser(@Param(value="userId")String userId,@Param("rentId") String rentId);
  	//商铺搜索
  	public List<Map<Object,Object>> queryRentShopsListBySearch(Map<String,Object> m);
  	//矩形区域商铺列表
    public List<Map<String,Object>> queryRentShopsLocationListBySearch(@Param("keyWord")String keyWord,@Param("leftLat")String leftLat,@Param("leftLng")String leftLng,@Param("rightLat")String rightLat,@Param("rightLng")String rightLng);
    //更新评论次数
    public int updateRentShopsCommentsFrequen(String rentId);
    //获取用户的求租商铺ID
    public List<String> queryRentIdsForUserId(String userId);
}
