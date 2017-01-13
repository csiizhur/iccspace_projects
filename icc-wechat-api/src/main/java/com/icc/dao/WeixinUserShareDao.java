package com.icc.dao;

/**
 * 
 * @description
 * @author zhurun
 * @date 2016年10月18日下午1:17:08
 */
public interface WeixinUserShareDao {

	//更新转发次数
	public int updateShopsHistoryForwardFrequen(String detailId);
	public int updateRentShopsForwardFrequen(String detailId);
	
	//查找detailId
	public int queryDetailIdForShopsHistoryTable(String detailId);
	public int queryDetailIdForRentShopsTable(String detailId);
	
	//查询最新转发次数
	public int queryForwardFrequenByShopsHistory(String detailId);
	public int queryForwardFrequenByRentShops(String detailId);
}
