package com.icc.service;

import java.util.List;
import java.util.Map;

public interface AddressService {

	Map<String,Object> getAllDataByCityNo(Map<String,Object> map);
	//所有城市和区域
	List<Map<String,Object>> getAllDataCityArea();
}
