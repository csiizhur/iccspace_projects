package com.icc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.UserLeaveMessageDao;
import com.icc.entity.UserLeaveMessage;
import com.icc.service.UserLeaveMessageService;
import com.icc.wechat.exception.ParametersException;
@Service
public class UserLeaveMessageServiceImpl implements UserLeaveMessageService {

	@Autowired
	private UserLeaveMessageDao userLeaveMessageDao;
	@Override
	public HeadMessageDto insertUserLeaveMessage(UserLeaveMessage ulm) {
		HeadMessageDto dto=new HeadMessageDto();
		if(StringUtils.isEmpty(ulm.getFromUserId())){
			throw new ParametersException("留言用户不能为空");
		}
		int result=userLeaveMessageDao.insertUserLeaveMessage(ulm);
		if(result==Constants.AFFECTED_ROWS_1){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_SUCCESS_INSERT_MSG);
		}else{
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage(MessageDictionary.RESP_ERROR_INSERT_MSG);
		}
		return dto;
	}

}
