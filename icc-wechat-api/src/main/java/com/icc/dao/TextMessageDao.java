package com.icc.dao;

import java.util.Map;

public interface TextMessageDao {

	String queryKeyWordForReply(Map<String,Object> map);
}
