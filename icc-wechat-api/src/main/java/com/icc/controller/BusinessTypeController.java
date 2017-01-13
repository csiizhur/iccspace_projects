package com.icc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.entity.BaseEntity;
import com.icc.entity.BusinessType;
import com.icc.service.BusinessTypeService;

@Controller
@RequestMapping("/businessType")
public class BusinessTypeController  {

	@Autowired
	private BusinessTypeService businessTypeService;
	
	@RequestMapping(method=RequestMethod.GET,value="/getAllCodeAndName")
	@ResponseBody
	public String getBusinessTypeCodeAndName(){
		BaseEntity be=new BaseEntity();
		List<BusinessType> list=businessTypeService.getAllBusinessTypeData();
		
		if(list==null){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_ERROR_MSG);
		}else if(list.size()==0){
			
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(list);
			
		}
		
		return JSONObject.toJSONString(be);
	}
}
