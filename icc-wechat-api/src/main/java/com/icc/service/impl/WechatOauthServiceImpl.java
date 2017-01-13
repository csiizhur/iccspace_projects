package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import com.icc.common.Constants;
import com.icc.common.WechatDictionary;
import com.icc.dao.UserDao;
import com.icc.dto.UserWechatDto;
import com.icc.entity.User;
import com.icc.entity.UserSessionDto;
import com.icc.service.WechatOauthService;
import com.icc.wechat.exception.ParametersException;

@Service
public class WechatOauthServiceImpl implements WechatOauthService {

	@Autowired
	private UserDao userDao;
	@Override
	public UserWechatDto getUserInfoByModelMap(ModelMap map,UserWechatDto userWechatDto) {
		String openId=(String) map.get("openId");
		UserSessionDto user=(UserSessionDto) map.get(Constants.SESSION_USER);

		if(StringUtils.isEmpty(openId) && user==null){
			throw new ParametersException(WechatDictionary.ERROR_CODE_N1, WechatDictionary.ERROR_CODE_N1_DESC);
		}else{
			User u=new User();
			if(openId!=null && !("").equals(openId)){
				
				u=userDao.getUserInfoByOpenId(openId);	
			}else{
				u=userDao.getUserInfoByOpenId(user.getOpenId());
			}
			userWechatDto.setUser(u);
		}
		return userWechatDto;
	}
	

}
