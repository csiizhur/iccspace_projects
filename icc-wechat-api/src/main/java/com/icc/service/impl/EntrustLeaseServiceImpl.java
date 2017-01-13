package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.EntrustLeaseDao;
import com.icc.entity.EntrustLease;
import com.icc.service.EntrustLeaseService;

@Service
public class EntrustLeaseServiceImpl implements EntrustLeaseService {

	@Autowired
	private EntrustLeaseDao entrustLeaseDao;
	
	/**
	 * 委托找店
	 */
	@Override
	public HeadMessageDto insertEntrustLease(EntrustLease entrustLease) {
		HeadMessageDto headMessageDto = new HeadMessageDto();
		String shopsId = entrustLease.getShopsId();
		int count = entrustLeaseDao.queryEntrustLeaseExist(shopsId);
		if(count>=1){
			headMessageDto.setCode(MessageDictionary.RESP_EXIST_ENTRUST_CODE);
			headMessageDto.setMessage(MessageDictionary.RESP_EXIST_ENTRUST_MSG);
			return headMessageDto;
		}
		int result = entrustLeaseDao.insertEntrustLease(entrustLease);
		if(Constants.AFFECTED_ROWS_1==result){
			headMessageDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			headMessageDto.setMessage(MessageDictionary.RESP_SUCCESS_INSERT_MSG);
		}else{
			headMessageDto.setCode(MessageDictionary.RESP_ERROR_CODE);
			headMessageDto.setMessage(MessageDictionary.RESP_ERROR_INSERT_MSG);
		}
		return headMessageDto;
	}

}
