package com.icc.service;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.RecommendRentUser;

public interface RecommendRentUserService {

	//添加推荐租户
	public HeadMessageDto insertRecommendRentUser(RecommendRentUser rru);
}
