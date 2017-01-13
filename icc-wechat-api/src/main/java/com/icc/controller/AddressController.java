package com.icc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icc.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController {

	@Resource
	private AddressService addressService;
	
	/**
	 * 加载all
	 * @param cityNo
	 * @param areaNo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/queryAllData2")
	@ResponseBody
	public JSON queryAllData(String cityNo,String areaNo){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(!StringUtils.isBlank(cityNo)){
			map.put("cityNo", cityNo);	
		}
		if(!StringUtils.isBlank(areaNo)){
			map.put("areaNo", areaNo);	
		}
		map=addressService.getAllDataByCityNo(map);
		return (JSON) JSON.toJSON(map);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/queryAllData")
	@ResponseBody
	public JSON queryAllData(){		
		List<Map<String,Object>> lc=addressService.getAllDataCityArea();
		return (JSON) JSON.toJSON(lc);
	}
}
