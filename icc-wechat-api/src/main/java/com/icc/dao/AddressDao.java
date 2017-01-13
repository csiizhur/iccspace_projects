package com.icc.dao;

import java.util.List;
import java.util.Map;

import com.icc.entity.AreaInfo;
import com.icc.entity.CityInfo;
import com.icc.entity.StreetInfo;

/**
 * 
 * @description
 * @author zhurun
 * @date 2016年10月18日下午1:17:08
 */
public interface AddressDao {

	//根据cityNo查询城市区域街道  结果集 =city * street
	CityInfo queryAllDataByCityNo(String cityNo);
	
	//根据cityNo查询区域
	CityInfo queryCityAndAreaByCityNo(String cityNo);
	
	//根据areaNo查询区域街道
	AreaInfo queryAreaAndStreetByAreaNo(String areaNo);
	
	//city
	List<CityInfo> queryCityInfo();
	List<Map<String,String>>queryAreaByCityNo(String cityNo);
	//area
	List<AreaInfo> queryAreaInfo();
	
	//street
	List<StreetInfo> queryStreetInfo();
}
