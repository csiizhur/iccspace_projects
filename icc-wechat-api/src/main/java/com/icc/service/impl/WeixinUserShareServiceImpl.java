package com.icc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.WeixinUserShareDao;
import com.icc.entity.WeixinUserShare;
import com.icc.service.WeixinUserShareService;
import com.icc.wechat.exception.ParametersException;
@Service
public class WeixinUserShareServiceImpl implements WeixinUserShareService {

	@Autowired
	private WeixinUserShareDao weixinUserShareDao;
	
	@Override
	public Map<String,Object> updateDetailsForwardFrequen(WeixinUserShare userShare) {

		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> body=new HashMap<String,Object>();
		HeadMessageDto dto=new HeadMessageDto();
		String detailId=userShare.getDetailId();
		int qryResult=0;
		int updateResult=0;
		int forwardFrequen=0;
		if(StringUtils.isNotEmpty(detailId)){
			qryResult=weixinUserShareDao.queryDetailIdForShopsHistoryTable(detailId);
			if(qryResult==1){
				updateResult=weixinUserShareDao.updateShopsHistoryForwardFrequen(detailId);
				//返回转发次数
				forwardFrequen=weixinUserShareDao.queryForwardFrequenByShopsHistory(detailId);
			}else{
				updateResult=weixinUserShareDao.updateRentShopsForwardFrequen(detailId);
				forwardFrequen=weixinUserShareDao.queryForwardFrequenByRentShops(detailId);
			}
			body.put("forwardFrequen", forwardFrequen);
		}else{
			throw new ParametersException("detailId is null");
		}
		if(updateResult==Constants.AFFECTED_ROWS_1){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_SUCCESS_UPDATE_MSG);
		}else{
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setCode(MessageDictionary.RESP_ERROR_UPDATE_MSG);
		}
		
		map.put("head", dto);
		map.put("body", body);
		return map;
	}

}
