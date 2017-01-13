package com.icc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.JSSDKConfig;

/**
 * 前端用户微信配置获取
 * @description
 * @author zhurun
 * @date 2016年11月23日上午11:12:20
 */
@Controller
@RequestMapping("/wechatconfig")
public class WechatJSSDKController {

private static Logger logger = LoggerFactory.getLogger(WechatJSSDKController.class);
	
	/**
	 * 跳转jssdk页面
	 * @return
	 */
	@RequestMapping("/toJSSDK")
	public String toJSSDK(){
		return "jssdkconfig";
	}
	/**headers = {"content-type=application/json","content-type=application/xml"}
	 * produces={"text/html", "application/json"}
	 * 将匹配“Accept:text/html”或“Accept:application/json”
	 * @Description: 前端获取微信JSSDK的配置参数
	 * @param @param response
	 * @param @param request
	 * @param @param url
	 * @param @throws Exception
	 * @author zhur
	 * @throws IOException 
	 * @date 2016年3月19日 下午5:57:52
	 */
	@RequestMapping(method=RequestMethod.POST,value="/jssdk2",produces="application/json;charset=UTF-8",headers="Accept=application/json")
	@ResponseBody	
	public JSONObject JSSDK_config2(@RequestBody UrlModel url,HttpServletResponse res) throws IOException {
		
		JSONObject json=new JSONObject();
		try {
			System.out.println(url);
			Map<String, String> configMap = JSSDKConfig.jsSDK_Sign(url.getUrl());
			logger.info("signature:"+configMap);
			json=(JSONObject) JSONObject.toJSON(configMap);
		} catch (Exception e) {
			logger.error("error:"+e);
		}
		return json;
		//res.setCharacterEncoding("utf-8");
		//res.setContentType("text/html;charset=utf-8");
		//res.getWriter().write(str);
		//res.getWriter().flush();
	}
	@RequestMapping(method=RequestMethod.POST,value="/jssdk")
	@ResponseBody	
	public JSONObject JSSDK_config(String url) throws IOException {
		
		JSONObject json=new JSONObject();
		try {
			System.out.println(url);
			Map<String, String> configMap = JSSDKConfig.jsSDK_Sign(url);
			logger.info("signature:"+configMap);
			json=(JSONObject) JSONObject.toJSON(configMap);
		} catch (Exception e) {
			logger.error("error:"+e);
		}
		return json;
	}
}
