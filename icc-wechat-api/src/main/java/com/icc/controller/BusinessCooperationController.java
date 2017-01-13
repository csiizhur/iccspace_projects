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
import com.icc.entity.BusinessCooperation;
import com.icc.service.BusinessCooperationService;

@Controller
@RequestMapping("/business_cooperation")
public class BusinessCooperationController {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BusinessCooperationService businessCooperationService;
	
	/**
	 * 商务合作添加
	 * @param rp
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addBusinessCooperation")
	public @ResponseBody JsonResult<HeadMessageDto> addBusinessCooperation(BusinessCooperation bc){
		JsonResult<HeadMessageDto> jsonResult=new JsonResultSupport<>();
		HeadMessageDto resu=businessCooperationService.insertBusinessCooperation(bc);
		jsonResult.setHead(resu);
		return jsonResult;
	}
	
}
