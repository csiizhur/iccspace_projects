package com.icc.service;

import java.util.List;
import java.util.Map;

public interface UserAccessShopsService {

	//30天内谁看过我
	public List<Map<String,Object>> getUserAccessIn30Days(String userId);
}
