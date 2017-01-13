package com.icc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.icc.entity.ShopsHistory;

public interface ShopsHistoryDao {

	ShopsHistory queryShopsHistoryInfo(String shopsId);
	/**
     * 需要使用乐观锁更新使用此方法
     * @param t
     * @return
     */
    int updateForOptimisticLocking(@Param("userClick")int userClick,@Param("shopsId")String shopsId,@Param("version")int version);
    int updateForOptimisticLocking(Map m);
    
    //编辑发布商铺
    public int updateHistoryShopInformationRelease(ShopsHistory sh);
    
    //更新主表的historyid
    public int updateBaseRentShopHistoryId(Map<String,Object> params);
    
    //矩形区域商铺列表
    public List<Map<String,Object>> queryShopsLocationList(@Param("keyWord")String keyWord,@Param("leftLat")String leftLat,@Param("leftLng")String leftLng,@Param("rightLat")String rightLat,@Param("rightLng")String rightLng);
    
    //更新商铺评论次数
    public int updateShopsHistoryCommentsFrequen(String shopsId);
    
    //Opensearch 需上传数据查询
    public List<Map<String,Object>> queryShopsHistoryForUpdateOpensearch();
    
    //获取用户发布的商铺ID
    public List<String> queryShopsIdsForUserId(String userId);
}
