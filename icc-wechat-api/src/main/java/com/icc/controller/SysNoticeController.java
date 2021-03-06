package com.icc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.entity.BaseEntity;
import com.icc.service.SysNoticeService;

@Controller
@RequestMapping("/sysnotice")
public class SysNoticeController {

	@Autowired
	private SysNoticeService sysNoticeService;
	
	@RequestMapping(method=RequestMethod.GET,value="/querySysNoticeList")
	public @ResponseBody String querySysNoticeList(Integer pageNo,Integer pageSize){
		PageInfo<Map<String,Object>> page=sysNoticeService.getSysNoticeList(pageNo,pageSize);
		BaseEntity be=new BaseEntity();
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		
		return JSONObject.toJSONString(be);
	}
}
