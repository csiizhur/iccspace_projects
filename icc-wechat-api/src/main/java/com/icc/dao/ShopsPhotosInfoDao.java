package com.icc.dao;

import java.util.List;

import com.icc.entity.ShopsHistory;
import com.icc.entity.ShopsPhotosInfo;

public interface ShopsPhotosInfoDao extends BaseDao<ShopsPhotosInfo, String>{

	//查询shops的图片
	List<ShopsPhotosInfo> queryShopsPhotosListByShopsId(String shopsId);
	
	//批量插入
	public int batchInsertPhotos(List<?> list);
	
	//批量更新
	public int batchUpdatePhotos(List<String> list);
	
	//关联查询
	public List<ShopsHistory> queryShopAndPhotos(String id);
	public List<ShopsHistory> queryShopAndPhotos2(String id);
}
