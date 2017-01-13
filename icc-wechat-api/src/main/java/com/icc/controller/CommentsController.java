package com.icc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.Comments;
import com.icc.service.UserCommentsService;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	private UserCommentsService userCommentsService;

	/**
	 * 添加评论
	 * @param com
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addUserComments")
	public @ResponseBody JsonResult<HeadMessageDto> addUsersComments(Comments com) {
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		HeadMessageDto dto=userCommentsService.insertShopsComments(com);
		
		result.setHead(dto);
		return result;
	}
	/**
	 * 评论列表
	 * @param shopsId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getShopsCommentsForShopsId")
	public @ResponseBody JsonResult<HeadMessageDto> queryShopsCommentsForShopsId(String shopsId){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		HeadMessageDto head=new HeadMessageDto();
		List<Map<String,Object>> list=userCommentsService.queryComments(shopsId);
		if(list.size()==0){
			head.setCode(MessageDictionary.RESP_NODATA_CODE);
			head.setMessage(MessageDictionary.RESP_DATAISNULL_MSG);
		}else{
			head.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			head.setMessage(MessageDictionary.RESP_QRY_MSG);
			result.setBody(list);	
		}
		result.setHead(head);
		return result;
	}
}
