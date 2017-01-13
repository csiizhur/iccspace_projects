package com.icc.service;

import java.util.Map;

import com.icc.dto.UserShopsDto;
import com.icc.entity.User;

public interface UserService {

	User getUserInfo(User user);
	User getUserInfoByUserId(String userId);
	User getUserInfoByOpenId(String openId);
	String queryUUID();
	int insertUsersInfo(User user);
	int updateUsersInfo(User user);
	int updateUserIdentitySwitch(User user);
	Integer queryUserIsExists(String openId);
	Integer queryUserIsSubscribe(String openId);
	
	//授权后返回用户信息
	public User insertUserAndUpdateUserRole(User user,int userRole);
	
	//租户详情
	public Map<String,Object> getTenantUsersInfoById(UserShopsDto dto);
	
	//修改图像
	public Map<String,Object> updateUserHeadImage(User user);
	
	//修改用户详细信息
	public boolean updateUserDetailsInformations(User user);
	//redis cache
	public User selectByPrimaryKey(String id);
	public void insertSelective(User user);
	public void deleteByPrimaryKey(String id);
}
