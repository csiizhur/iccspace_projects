package com.icc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.UserMessageDao;
import com.icc.entity.UserMessage;
import com.icc.service.UserMessageService;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpUtils;
import com.icc.wechat.message.model.TemplateData;
@Service
public class UserMessageServiceImpl implements UserMessageService {

	private static final String TEMPLTATE_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	//private static final String TEMPLTATE_MESSAGE="https://api.weixin.qq.com/cgi-bin/message/template/send";
	
	private final String LONG_TEMPLETE_ID="s-P4_WCwAyyX6Of5n74zHp2S2g2DHr0ArdiyAr52n-c";
	private Logger logger =LoggerFactory.getLogger(getClass());
	@Autowired
	private UserMessageDao userMessageDao;
	@Override
	public HeadMessageDto sendUserMessage(UserMessage um) {
		HeadMessageDto dto=new HeadMessageDto();
		int result=userMessageDao.insertUserMessage(um);
		try {
			//模版消息通知
			Map<String, Object> params = new HashMap<String, Object>();
			//params.put("access_token", GlobalConstantsPropertiesUtil.getString("access_token"));
			params.put("touser", um.getToUserId());
			params.put("template_id", LONG_TEMPLETE_ID);
			params.put("url", "http://weixin.qq.com/download");
			params.put("topcolor", "#FF0000");
			/*TemplateData data = new TemplateData();
			Map<String,String> m=new HashMap<String,String>();
			m.put("value", "收到一条消息");
			m.put("color", "#173177");
			data.setFirst(m);
			params.put("data", JSONObject.toJSONString(data));*/
			Map<String,Object> data=new HashMap<String,Object>();
			Map<String,String> data2=new HashMap<String,String>();
			data2.put("value", "xx");
			data2.put("color", "#173177");
			data.put("User", data2);
			params.put("data", data);
			HttpUtils.sendPostBuffer(TEMPLTATE_MESSAGE+GlobalConstantsPropertiesUtil.getString("access_token"), JSONObject.toJSONString(params));
		} catch (Exception e) {
			logger.error("模版消息发送失败");
		}
		if(result==Constants.AFFECTED_ROWS_1){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}else{
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}
		return dto;
	}
	@Override
	public List<Map<String,Object>> userAllMessageList(String toUserId) {
		List<Map<String,Object>> msgList=userMessageDao.queryUserMessageListByToUserId(toUserId);
		return msgList;
	}
	@Override
	public Map<String,Object> fromUserMessageList(UserMessage um) {
		//用户消息是否存在 存在返回消息，不存在返回用户信息
		int messageExist=userMessageDao.queryUserMessageExist(um);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> msgList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> userList=new ArrayList<Map<String,Object>>();
		/*if(messageExist>0){
			//更新消息状态
			int updateResult=userMessageDao.updateUserMessageReadFlg(um);
			//用户信息
			//消息信息
			msgList=userMessageDao.queryFromUserMessageList(um);
			
		}else{
			//返回用户信息
			msgList=userMessageDao.queryTwoUsersInfo(um);
		}*/
		//返回：用户信息+消息信息
		if(messageExist>0){
			//更新消息状态
			int updateResult=userMessageDao.updateUserMessageReadFlg(um);
			//消息信息
			msgList=userMessageDao.queryFromUserMessageNoUserInfoListNoTime(um);
			
		}
		//返回用户信息
		userList=userMessageDao.queryTwoUsersInfo(um);
		if (userList.size()>0) {
			map.put("msgList", msgList);
			map.put("userList", userList);
		}
		return map;
	}

}
