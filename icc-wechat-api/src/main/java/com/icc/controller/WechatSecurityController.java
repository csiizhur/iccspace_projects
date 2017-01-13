package com.icc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icc.util.MessageUtil;
import com.icc.util.SignUtil;
import com.icc.wechat.dispatcher.EventDispatcher;
import com.icc.wechat.dispatcher.MsgDispatcher;

/**
 * 
 * @description 微信服务端验证
 * @author zhur
 * @date 2016年9月9日上午9:53:03
 */

@Controller
@RequestMapping(value="/wechat")
public class WechatSecurityController {

	private static Logger logger=LoggerFactory.getLogger(WechatSecurityController.class);
	
	@Autowired
	private MsgDispatcher msgDispatcher;
	@Autowired
	private EventDispatcher eventDispatcher;
	
	@RequestMapping(method=RequestMethod.GET,value="/security")
	public @ResponseBody String doGetByWechat(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestParam(value="signature",required=true) String signature,
			@RequestParam(value="timestamp",required=true) String timestamp,
			@RequestParam(value="nonce",required=true) String nonce,
			@RequestParam(value="echostr",required=true) String echostr
			){
		
		try {
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				//PrintWriter out = res.getWriter();
				//out.print(echostr);
				//out.close();
				
				return echostr;
			}else{
				logger.error("非法请求@@");
				return "";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.error(echostr);
		return null;
		
	}
	
	/**
	 * 微信服务端的消息事件处理
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/security")
	public @ResponseBody String doPOST(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		resp.setCharacterEncoding("UTF-8");
		Map<String,String> map=MessageUtil.parseXml(req);
		String messageType=map.get("MsgType");
		String respMsg=null;
		String json=null;
		if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(messageType)){
			respMsg=eventDispatcher.processEvent(map);
			json=JSONObject.toJSONString(respMsg);
		}else{
			respMsg=msgDispatcher.processMessage(map); //进入消息处理
			if(respMsg==null || ("").equals(respMsg)){
				json= "success";
			}else{		
				logger.info(respMsg);
				json= JSONObject.toJSONString(respMsg);
			}
		}
		return json;
	}
}
