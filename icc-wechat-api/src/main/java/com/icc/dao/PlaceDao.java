package com.icc.dao;

import java.util.List;
import java.util.Map;

public interface PlaceDao {

	//查询租户的期望业态
	public List<Map<String,String>> queryUserExpectBusinessType(String rentUserId);
}
