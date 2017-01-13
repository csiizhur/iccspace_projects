package com.icc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.BusinessCooperationDao;
import com.icc.entity.BusinessCooperation;
import com.icc.service.BusinessCooperationService;
import com.icc.wechat.exception.ParametersException;
@Service
public class BusinessCooperationServiceImpl implements BusinessCooperationService {

	@Autowired
	private BusinessCooperationDao businessCooperationDao;
	@Override
	public HeadMessageDto insertBusinessCooperation(BusinessCooperation bc) {
		HeadMessageDto dto=new HeadMessageDto();
		if(StringUtils.isEmpty(bc.getCooperationUserId())){
			throw new ParametersException("合作用户不能为空");
		}
		int result=businessCooperationDao.insertBusinessCooperation(bc);
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
