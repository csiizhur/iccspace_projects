package com.icc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.EntrustLease;
import com.icc.service.EntrustLeaseService;

@Controller
@RequestMapping("entrust")
public class EntrustLeaseController {

	@Autowired
	private EntrustLeaseService entrustLeaseService;
	
	/**
	 * 新增委托
	 * @param entrustLease
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,value="add")
	@ResponseBody
	public JsonResult<HeadMessageDto> addEntrustLease(EntrustLease entrustLease){
		JsonResult<HeadMessageDto> result = new JsonResultSupport<>();
		HeadMessageDto dto = new HeadMessageDto();
		String userId = entrustLease.getEntrustUserId();
		String shopsId = entrustLease.getShopsId();
		if(StringUtils.isEmpty(userId)){
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage("user id is null");
			result.setHead(dto);
			return result;
		}
		if(StringUtils.isEmpty(shopsId)){
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage("shops id is null");
			result.setHead(dto);
			return result;
		}
		entrustLease.setAgentSchedule(1);//1：确认委托  2：预付定金  3：寻找租户 4：完成
		dto = entrustLeaseService.insertEntrustLease(entrustLease);
		result.setHead(dto);
		return result;
	}
}
