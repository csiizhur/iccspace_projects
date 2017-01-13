package com.icc.service;

import org.springframework.ui.ModelMap;

import com.icc.dto.UserWechatDto;

public interface WechatOauthService {

	public UserWechatDto getUserInfoByModelMap(ModelMap map,UserWechatDto userWechatDto);
}
