package com.icc.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.icc.service.BlackListService;

@Controller
@RequestMapping("/blacklist")
public class BlackListController {

	
	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 加入黑名单
	 * @param userId
	 * @param blackUserId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/insertBlackListUsers")
	public @ResponseBody String insertBlackListUsers(@RequestParam String userId,String blackUserId){
		BaseEntity be=new BaseEntity();
		Map<String,Object> blackMap=new HashMap<String,Object>();
		int result=blackListService.insertBlackListUsers(blackMap);
		if(result==1){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_ERROR_MSG);
			
		}
		return JSONObject.toJSONString(be);
	}
}
