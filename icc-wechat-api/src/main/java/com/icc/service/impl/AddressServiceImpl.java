package com.icc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.AddressDao;
import com.icc.entity.AreaInfo;
import com.icc.entity.CityInfo;
import com.icc.entity.StreetInfo;
import com.icc.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public Map<String,Object> getAllDataByCityNo(Map<String, Object> map) {
		
		if(map.size()==0){
			List<CityInfo> citys=addressDao.queryCityInfo();
			List<AreaInfo> areas=addressDao.queryAreaInfo();
			List<StreetInfo> streets=addressDao.queryStreetInfo();
			map.put("citys", citys);
			map.put("areas", areas);
			map.put("streets", streets);
		}
		String cityNo=(String) map.get("cityNo");
		String areaNo=(String) map.get("areaNo");
		if(!StringUtils.isBlank(cityNo)){
			CityInfo city=addressDao.queryCityAndAreaByCityNo((String)map.get("cityNo"));
			
			map.put("city", city);
		}
		if(!StringUtils.isBlank(areaNo)){
			
			AreaInfo area=addressDao.queryAreaAndStreetByAreaNo((String)map.get("areaNo"));
			map.put("area", area);
		}
		return map;
	}

	@Override
	public List<Map<String,Object>> getAllDataCityArea() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		List<CityInfo> cs=addressDao.queryCityInfo();
		for(CityInfo css:cs){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("cityName", css.getCityName());
			map.put("cityNo", css.getCityNo());
			String cityNo=css.getCityNo();
			List<Map<String,String>> area=addressDao.queryAreaByCityNo(cityNo);
			map.put("areaList", area);
			list.add(map);
		}
		return list;
	}

}
