package com.icc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.UserLeaveMessage;
import com.icc.service.UserLeaveMessageService;

@Controller
@RequestMapping("/leavemessage")
public class UserLeaveMessageController {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserLeaveMessageService userLeaveMessageService;
	
	/**
	 * 商务合作添加
	 * @param rp
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addUserLeaveMessage")
	public @ResponseBody JsonResult<HeadMessageDto> addUserLeaveMessage(UserLeaveMessage ulm){
		JsonResult<HeadMessageDto> jsonResult=new JsonResultSupport<>();
		HeadMessageDto resu=userLeaveMessageService.insertUserLeaveMessage(ulm);
		jsonResult.setHead(resu);
		return jsonResult;
	}
	
}
