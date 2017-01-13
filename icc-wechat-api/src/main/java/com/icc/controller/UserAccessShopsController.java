package com.icc.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.service.UserAccessShopsService;
@Controller
@RequestMapping("/access")
public class UserAccessShopsController {

	@Autowired
	private UserAccessShopsService userAccessShopsService;
	/**
	 * 谁看过我
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getUserAccessShops")
	public @ResponseBody JsonResult<HeadMessageDto> getUserAccessShops(String userId){
		HeadMessageDto head=new HeadMessageDto();
		JsonResult<HeadMessageDto> js=new JsonResultSupport<>();
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		if(StringUtils.isEmpty(userId)){
			head.setCode(MessageDictionary.RESP_NO_USERID_CODE);
			head.setMessage(MessageDictionary.RESP_USERID_IS_NOTNULL_MSG);
		}else{
			result=userAccessShopsService.getUserAccessIn30Days(userId);
			head.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			head.setMessage(MessageDictionary.RESP_QRY_MSG);
		}
		js.setHead(head);
		js.setBody(result);
		return js;
	}
}
