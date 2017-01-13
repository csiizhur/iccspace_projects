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
import com.icc.entity.RecommendProperty;
import com.icc.entity.RecommendRentUser;
import com.icc.service.RecommendPropertyService;
import com.icc.service.RecommendRentUserService;

@Controller
@RequestMapping("/recommend")
public class RecommendPropertyAndRentUserController {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RecommendPropertyService recommendPropertyService;
	
	@Autowired
	private RecommendRentUserService recommendRentUserService;
	/**
	 * 推荐房源添加
	 * @param rp
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addRecommendProperty")
	public @ResponseBody JsonResult<HeadMessageDto> addRecommendProperty(RecommendProperty rp){
		JsonResult<HeadMessageDto> jsonResult=new JsonResultSupport<>();
		HeadMessageDto resu=recommendPropertyService.insertRecommendProperty(rp);
		jsonResult.setHead(resu);
		return jsonResult;
	}
	
	/**
	 * 推荐租户添加
	 * @param rp
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addRecommendRentUser")
	public @ResponseBody JsonResult<HeadMessageDto> addRecommendRentUser(RecommendRentUser rru){
		JsonResult<HeadMessageDto> jsonResult=new JsonResultSupport<>();
		HeadMessageDto resu=recommendRentUserService.insertRecommendRentUser(rru);
		jsonResult.setHead(resu);
		return jsonResult;
	}
}
