package com.icc.service;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.UserLeaveMessage;

public interface UserLeaveMessageService {

	//添加留言
	public HeadMessageDto insertUserLeaveMessage(UserLeaveMessage ulm);
}
