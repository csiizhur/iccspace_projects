package com.icc.dao;

import java.util.List;
import java.util.Map;

import com.icc.dto.UserExpectShopsDto;
import com.icc.entity.User;

public interface UserDao {

	User getUserInfo(User user);
	//根据userID查询信息
	User getUserInfoByUserId(String userId);
	//根据openId查询信息
	User getUserInfoByOpenId(String openId);
	//UUID
	String queryUUID();
	//新增用户
	int insertUsersInfo(User user);
	//更新用户
	int updateUsersInfo(User user);
	//身份切换
	int updateUserIdentitySwitch(User user);
	//是否存在
	Integer queryUserIsExists(String openId);
	//是否关注
	Integer queryUserIsSubscribe(String openId);
	
	//调用存储过程
	public User queryUserinfoByProcedure(int userRole);
	//多字段排序样例
	public List<Map> queryUsersSort(Map<String,Object> m);
	
	//出租用户期望信息
	public UserExpectShopsDto queryUserExceptShops(String userId);
	//求租用户期望信息
	public UserExpectShopsDto queryUserExceptRentShops(String userId);
	
	//修改图像
	int updateUserHeadImage(User user);
	
	//修改详细信息
	int updateUserMoreInformation(User user);
	
	//redis测试删除
	int deleteByPrimaryKey(String id);
}
