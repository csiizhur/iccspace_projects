package com.icc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.WeixinUserShare;
import com.icc.service.WeixinUserShareService;

@Controller
@RequestMapping("/shareDetail")
public class WeixinShareController {

	@Autowired
	private WeixinUserShareService weixinUserShareService;
	/**
	 * 微信分享转发
	 * @param userShare
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/userShare")
	public @ResponseBody JsonResult<HeadMessageDto> shopDetailPageByUserShare(WeixinUserShare userShare){
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		Map<String,Object> map=weixinUserShareService.updateDetailsForwardFrequen(userShare);
		jr.setHead((HeadMessageDto)map.get("head"));
		jr.setBody(map.get("body"));
		return jr;
	}
}
