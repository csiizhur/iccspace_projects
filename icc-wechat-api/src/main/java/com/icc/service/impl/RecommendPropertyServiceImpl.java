package com.icc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.RecommendPropertyDao;
import com.icc.entity.RecommendProperty;
import com.icc.service.RecommendPropertyService;
import com.icc.wechat.exception.ParametersException;
@Service
public class RecommendPropertyServiceImpl implements RecommendPropertyService {

	@Autowired
	private RecommendPropertyDao recommendPropertyDao;
	@Override
	public HeadMessageDto insertRecommendProperty(RecommendProperty rp) {
		HeadMessageDto dto=new HeadMessageDto();
		if(StringUtils.isEmpty(rp.getRecommendUserId())){
			throw new ParametersException("推荐用户不能为空");
		}
		int result=recommendPropertyDao.insertRecommendProperty(rp);
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
