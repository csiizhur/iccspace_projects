package cn.iccspace.dao;

import java.util.List;

import cn.iccspace.entity.UserInfo;

public interface UserInfoDao {

	public UserInfo queryUserForUserId(String userId);
	
	public List<UserInfo> queryAllUsers();
}
