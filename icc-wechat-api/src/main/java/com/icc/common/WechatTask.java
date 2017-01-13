package com.icc.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpUtils;
import com.icc.wechat.common.WechatConstants;

/**
 * 
 * @description 执行的定时任务(access_token获取)
 * @author zhur
 * @date 2016年9月18日下午2:10:24
 */
public class WechatTask {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void getToken() throws Exception{
		Map<String,String> params=new HashMap<String,String>();
		
		params.put("grant_type", "client_credential");
		params.put("appid", GlobalConstantsPropertiesUtil.getString("appid"));
		params.put("secret", GlobalConstantsPropertiesUtil.getString("AppSecret"));
		
		String token=HttpUtils.sendGet(GlobalConstantsPropertiesUtil.getString(WechatConstants.access_token_url), params);
		String access_token=JSONObject.parseObject(token).getString(WechatConstants.access_token);
		
		GlobalConstantsPropertiesUtil.interfaceUrlProperties.put(WechatConstants.access_token, access_token);
	
		logger.error(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token为===>"+access_token);
		getTicket(access_token);
	}
	
	public void getTicket(String access_token) throws Exception{
		Map<String,String> params=new HashMap<String,String>();
		//获取jsticket的执行体
        params.clear();
        params.put(WechatConstants.access_token, access_token);
        params.put("type", WechatConstants.jsapi_ticket_type);
        String jsticket = HttpUtils.sendGet(GlobalConstantsPropertiesUtil.getString(WechatConstants.jsapi_ticket_url), params);
        String jsapi_ticket = JSONObject.parseObject(jsticket).getString(WechatConstants.jsapi_ticket_name); 
        GlobalConstantsPropertiesUtil.interfaceUrlProperties.put(WechatConstants.jsapi_ticket, jsapi_ticket); // 获取到js-SDK的ticket并赋值保存
         
        logger.error("ticket为===>" + jsapi_ticket);
        
	}
	
}
