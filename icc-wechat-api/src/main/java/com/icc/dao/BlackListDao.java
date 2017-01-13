package com.icc.dao;

import java.util.Map;

/**
 * 
 * @description 黑名单
 * @author zhur
 * @date 2016年9月13日下午3:30:36
 */
public interface BlackListDao {

	int insertBlackListUsers(Map<String,Object> map);
}
