package com.icc.dao;

import java.util.List;
import java.util.Map;

import com.icc.entity.UserMessage;

public interface UserMessageDao {

	//发送消息
	//public int insertUserMessage(@Param("fromUserId")String fromUserId,@Param("toUserId")String toUserId);
	public int insertUserMessage(UserMessage um);
	
	//用户消息列表
	public List<Map<String,Object>> queryUserMessageList(String toUserId);
	//双向消息
	public List<Map<String,Object>> queryUserMessageListByToUserId(String toUserId);
	//更新消息已读
	public int updateUserMessageReadFlg(UserMessage um);
	//单个用户消息列表
	public List<Map<String,Object>> queryFromUserMessageList(UserMessage um);
	//去掉用户信息
	public List<Map<String,Object>> queryFromUserMessageNoUserInfoList(UserMessage um);
	//去掉时间(一个月内)
	public List<Map<String,Object>> queryFromUserMessageNoUserInfoListNoTime(UserMessage um);
	//用户消息是否存在
	public int queryUserMessageExist(UserMessage um);
	//查询两个用户信息
	public List<Map<String,Object>> queryTwoUsersInfo(UserMessage um);
}
