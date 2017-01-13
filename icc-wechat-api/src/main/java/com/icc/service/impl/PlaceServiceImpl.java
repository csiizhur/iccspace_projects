package com.icc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.message.CHECKMSG;
import com.icc.common.message.ResourceManager;
import com.icc.dao.PlaceDao;
import com.icc.service.PlaceService;
import com.icc.wechat.exception.ParametersException;
@Service
public class PlaceServiceImpl implements PlaceService {

	public static final ResourceManager checkmsg = ResourceManager.getInstance("com.icc.common.message.checkmsg");
	@Autowired
	private PlaceDao placeDao;
	@Override
	public List<Map<String, String>> queryUserExpectBusiness(String rentUserId) {

		if(StringUtils.isNotEmpty(rentUserId)){
			return placeDao.queryUserExpectBusinessType(rentUserId);	
		}else{
			throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_RENTUSERID_ISNULL_ERROR, rentUserId));
		}
	}

}
