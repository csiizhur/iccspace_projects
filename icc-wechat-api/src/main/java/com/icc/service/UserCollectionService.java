package com.icc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.icc.common.model.HeadMessageDto;
import com.icc.entity.UserCollection;

public interface UserCollectionService {

	
	PageInfo<UserCollection> getUsersCollectionShopsList(String userId,Integer pageNo,Integer pageSize);
	
	//商铺收藏集
	List<Map<String,Object>> getUsersCollectionShopsList(String userId);
	
	//收藏商铺
	int insertCollectionShopsByUser(String userId,String shopsId);
	
	//收藏批量删除
	int deleteCollectionShopsByUser(String deleteUserId,List<String> ids);
	
	//删除一个收藏
	HeadMessageDto deleteOneCollectionShopsByUser(String shopsId,String userId);
}
