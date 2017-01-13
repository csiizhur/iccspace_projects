package com.icc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.entity.BaseEntity;
import com.icc.entity.UserSessionDto;
import com.icc.entity.UserWallet;
import com.icc.service.UserWalletService;

@Controller
@RequestMapping("/wallet")
@SessionAttributes(Constants.SESSION_USER)
public class UserWalletController {

	private Logger logger=LoggerFactory.getLogger(UserWalletController.class);
	
	@Autowired
	private UserWalletService userWalletService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET,value="/getUserWalletInfo")
	public @ResponseBody String getUserWalletInfo(@RequestParam String userId,ModelMap model){
		BaseEntity be=new BaseEntity();
		UserSessionDto u=(UserSessionDto)model.get(Constants.SESSION_USER);
		if(userId==null || ("").equals(userId)){
			userId=u.getUserId();
		}
		UserWallet uw=userWalletService.getUserWalletInfo(userId);
		if(uw!=null){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(uw);
		}else{
			be.getHead().put("code", "0001");
			be.getHead().put("message", "该钱包信息不存在");
		}
		
		return JSONObject.toJSONString(be);
	}
}
