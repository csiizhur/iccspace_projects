package com.icc.dao;

import java.util.Map;

public interface PropertysDao {

	Map<String,Object> queryPropertyInfoForShopsId(String shopsId);
}
