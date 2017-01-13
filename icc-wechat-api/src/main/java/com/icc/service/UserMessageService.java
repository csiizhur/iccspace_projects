package com.icc.service;

import java.util.List;
import java.util.Map;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.UserMessage;

public interface UserMessageService {
	//消息发送
	public HeadMessageDto sendUserMessage(UserMessage um);
	//用户消息列表
	public List<Map<String,Object>> userAllMessageList(String toUserId);
	//用户消息详情列表
	public Map<String,Object> fromUserMessageList(UserMessage um);
}
