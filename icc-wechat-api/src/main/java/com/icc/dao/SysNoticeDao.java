package com.icc.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @description 公告Dao接口
 * @author zhur
 * @date 2016年9月13日上午9:58:46
 */
public interface SysNoticeDao {

	//公告查询的列表
	List<Map<String,Object>> querySysNoticeList();
}
