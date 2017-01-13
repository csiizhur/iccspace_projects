package com.icc.service;

import java.util.List;
import java.util.Map;

import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.Location;
import com.icc.entity.ShopsHistory;

public interface ShopsHistoryService {
	
	//编辑商铺发布
	public HeadMessageDto editReleaseShopsByUser(ShopsHistory sh,String shopsId,String baseShopId,List<String> delIds, List<String> addDatas,Map<String,Object> params);
	public HeadMessageDto editReleaseShopsByUserWeixin(ShopsHistory sh,String shopsId,String baseShopId,List<String> delIds, List<String> addDatas,Map<String,Object> params);
	
	//矩形区域商铺坐标列表
	public String queryShopsLocationList(Integer pageNo,String keyWord,Location left,Location right);
}
