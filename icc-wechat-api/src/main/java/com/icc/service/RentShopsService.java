package com.icc.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.icc.common.model.HeadMessageDto;
import com.icc.entity.RentShops;

public interface RentShopsService {
	
	//编辑商铺发布(求租)
	public HeadMessageDto editReleaseRentShopsByUser(RentShops rs,List<String> addDatas,List<String> delIds);
	public HeadMessageDto editReleaseRentShopsByUserWeixin(RentShops rs,List<String> addDatas,List<String> delIds);
	//搜索(求租)
	public PageInfo<Map<Object,Object>> getRentShopsListBySearch(Map<String,Object> m,Integer pageNum);
}
