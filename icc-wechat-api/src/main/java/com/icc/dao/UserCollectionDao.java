package com.icc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.icc.entity.UserCollection;

public interface UserCollectionDao {
	//收藏列表
	List<UserCollection> getUsersCollectionShopsListForPage(String userId);
	List<Map<String,Object>> getUsersCollectionShopsList(String userId);
	//收场求租列表
	List<Map<String,Object>> getUsersCollectionRentShops(String userId);
	//是否收藏过
	UserCollection queryIsCollectionShopsByUser(@Param("userId")String userId,@Param("shopsId")String shopsId);
	//添加单条收藏
	int insertCollectionShopsByUser(@Param(value = "userId") String userId,@Param("shopsId")String shopsId);
	//更新单条收藏
	int updateOneCollectionShopsByUser(@Param("userId")String userId,@Param("shopsId")String shopsId);
	//更新收藏次数(物业的)
	int updateShopsHistoryCollectionFrequen(String shopsId);
	int updateShopsHistoryCollectionFrequen2(String shopsId);
	//更新收藏次数(求租的)
	int updateRentShopsCollectionFrequen(String rentId);
	int updateRentShopsCollectionFrequen2(String rentId);
	//更新单条记录
	int deleteCollectionShopsByUser(@Param("deleteUserId")String deleteUserId,@Param("Id")String Id);
	//批量更新 in
	int batchDeleteCollectionShopsByUser2(@Param("list")List<?> collectionIds,@Param("deleteUserId")String deleteUserId);
	//批量更新 for
	int batchDeleteCollectionShopsByUser(@Param("collectionIds")List<?> collectionIds,@Param("deleteUserId")String deleteUserId);
	//根据shopId和当前用户Id判断是否收藏
	String queryUserIsCollectionShops(@Param("userId")String userId,@Param("shopsId") String shopsId);
	//更新一个收藏
	int deleteOneCollectionShopsByUser(@Param("deleteUserId")String deleteUserId,@Param("userId")String userId,@Param("shopsId")String shopsId);
}
