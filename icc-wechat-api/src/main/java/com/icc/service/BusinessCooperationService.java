package com.icc.service;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.BusinessCooperation;

public interface BusinessCooperationService {

	//添加商务合作
	public HeadMessageDto insertBusinessCooperation(BusinessCooperation bc);
}
