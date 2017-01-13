package com.icc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.icc.entity.UserAccessShops;

public interface UserAccessShopsDao {

	//添加访问记录
	public int insertUserAccessShops(UserAccessShops uas);
	//查询
	public UserAccessShops queryUserAccessShops(UserAccessShops uas);
	//乐观锁-用于更新记录
	public int updateForOptimisticLocking(UserAccessShops uas);
	//30看过我的记录
	//public List<Map<String,Object>> queryUsersAccessIn30Days(String[] shopsIds);
	public List<Map<String,Object>> queryUsersAccessIn30Days(@Param("list")List<String> shopsIds);
}
