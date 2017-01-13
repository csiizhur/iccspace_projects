package com.icc.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.entity.BaseEntity;
import com.icc.service.PropertysService;

@Controller
@RequestMapping("/propertys")
public class PropertysController {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PropertysService propertysService;
	
	@RequestMapping(method=RequestMethod.GET,value="/getPropertysDetail")
	public @ResponseBody String getPropertysDetailForShopsId(@RequestParam String shopsId){
		BaseEntity be=new BaseEntity();
		Map<String,Object> propertysDetail=propertysService.queryPropertysInfoByShopsId(shopsId);
		if(propertysDetail!=null){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(propertysDetail);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
	}
}
