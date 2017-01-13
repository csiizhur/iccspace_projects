package com.icc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.UserMessage;
import com.icc.service.UserMessageService;

@RequestMapping("/message")
@Controller
public class UserMessageController {

	@Autowired
	private UserMessageService userMessageService;
	
	/**
	 * 消息发送
	 * @param um
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/sendMessage2",produces="application/json;charset=UTF-8")
	public @ResponseBody JsonResult<HeadMessageDto> sendUserMessage(@RequestBody UserMessage um){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<>();
		HeadMessageDto dto=userMessageService.sendUserMessage(um);
		result.setHead(dto);
		return result;
	}
	/**
	 * 消息发送 no json
	 * @param um
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/sendMessage",produces="application/json;charset=UTF-8")
	public @ResponseBody JsonResult<HeadMessageDto> sendUserMessageNoJson(UserMessage um){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<>();
		HeadMessageDto dto=userMessageService.sendUserMessage(um);
		result.setHead(dto);
		return result;
	}
	
	/**
	 * 我的消息列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/listUserMessage")
	public @ResponseBody JsonResult<HeadMessageDto> listUserMessage(String toUserId){
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		HeadMessageDto dto=new HeadMessageDto();
		List<Map<String,Object>> msgList=userMessageService.userAllMessageList(toUserId);
		if(msgList.size()>Constants.QUERY_DB_DATA_COUNT){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_QRY_MSG);
			jr.setBody(msgList);
		}else{
			dto.setCode(MessageDictionary.RESP_NODATA_CODE);
			dto.setMessage(MessageDictionary.RESP_DATAISNULL_MSG);
		}
		jr.setHead(dto);
		return jr;
	}
	/**
	 * 收到该用户的详细消息
	 * @param um
	 * @return
	 */
	//@RequestMapping(method=RequestMethod.GET,value="/fromUserMessageList",produces="application/json;charset=UTF-8")
	@RequestMapping(method=RequestMethod.GET,value="/fromUserMessageList")
	public @ResponseBody JsonResult<HeadMessageDto> listUserMessageDetail(UserMessage um){
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		HeadMessageDto dto=new HeadMessageDto();
		Map<String,Object> resultList=userMessageService.fromUserMessageList(um);
		if(resultList.size()>Constants.QUERY_DB_DATA_COUNT){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_QRY_MSG);
			jr.setBody(resultList);
		}else{
			dto.setCode(MessageDictionary.RESP_NODATA_CODE);
			dto.setMessage(MessageDictionary.RESP_DATAISNULL_MSG);
		}
		jr.setHead(dto);
		return jr;
	}
}
