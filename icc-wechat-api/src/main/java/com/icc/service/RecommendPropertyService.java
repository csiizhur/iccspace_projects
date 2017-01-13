package com.icc.service;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.RecommendProperty;

public interface RecommendPropertyService {

	//添加推荐房源
	public HeadMessageDto insertRecommendProperty(RecommendProperty rp);
}
