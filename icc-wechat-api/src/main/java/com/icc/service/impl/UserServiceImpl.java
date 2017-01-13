package com.icc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.WechatDictionary;
import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.dao.UserCollectionDao;
import com.icc.dao.UserDao;
import com.icc.dto.UserShopsDto;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.entity.User;
import com.icc.service.UserService;
import com.icc.wechat.exception.ParametersException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShopsDao shopsDao;
	
	@Autowired
	private ShopsPhotosInfoDao shopsPhotosInfoDao;
	
	@Autowired
	private RentShopsDao rentShopsDao;
	
	@Autowired
	private UserCollectionDao userCollectionDao;
	@Override
	public User getUserInfo(User user) {
		return userDao.getUserInfo(user);
	}
	@Override
	public User getUserInfoByUserId(String userId) {
		return userDao.getUserInfoByUserId(userId);
	}
	@Override
	public String queryUUID() {
		return userDao.queryUUID();
	}
	
	@Override
	public int insertUsersInfo(User user) {
		return userDao.insertUsersInfo(user);
	}
	@Override
	public int updateUserIdentitySwitch(User user) {
		return userDao.updateUserIdentitySwitch(user);
	}
	@Override
	public Integer queryUserIsSubscribe(String openId) {
		return userDao.queryUserIsSubscribe(openId);
	}
	@Override
	public Integer queryUserIsExists(String openId) {
		return userDao.queryUserIsExists(openId);
	}
	@Override
	public int updateUsersInfo(User user) {
		return userDao.updateUsersInfo(user);
	}
	@Override
	public User getUserInfoByOpenId(String openId) {
		return userDao.getUserInfoByOpenId(openId);
	}
	@Override
	public User insertUserAndUpdateUserRole(User user,int userRole) {

		int result=userDao.insertUsersInfo(user);
		if(result==1){
			if (String.valueOf(userRole)!=null) {
				user.setUserRole(userRole);
				userDao.updateUserIdentitySwitch(user);
				return user;
			}else{
				throw new ParametersException(WechatDictionary.ERROR_CODE_N1, WechatDictionary.ERROR_CODE_N1_DESC);
			}
		}else{
			throw new ParametersException(WechatDictionary.ERROR_CODE_N2, WechatDictionary.ERROR_CODE_N2_DESC);
		}
	}
	@Override
	public Map<String, Object> getTenantUsersInfoById(UserShopsDto dto) {
		String userId=dto.getRentUserId();
		String shopsId=dto.getShopsId();
		String currentUserId=dto.getCurrentUserId();
		Map<String,Object> resultmap=new HashMap<String,Object>();
		if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(userId)){
			throw new ParametersException(WechatDictionary.ERROR_CODE_N1, WechatDictionary.ERROR_CODE_N1_DESC);
		}else{
			//用户信息
			User u=userDao.getUserInfoByUserId(userId);
			//商铺信息
			Map<String,Object> shopsMap=rentShopsDao.queryRentShopsInfoById(shopsId);
			//商铺图片
			List<ShopsPhotosInfo> ps=shopsPhotosInfoDao.queryShopsPhotosListByShopsId(shopsId);
			//是否被当前用户收藏
			String ucId;
			if (currentUserId!=null && shopsId!=null) {
				ucId = userCollectionDao.queryUserIsCollectionShops(currentUserId, shopsId);
				if(ucId!=null){
					resultmap.put("isCollection", true);
				}else{
					resultmap.put("isCollection", false);
				}
			}else{
				throw new IllegalArgumentException("用户未登录");
			}
			resultmap.put("userInfo", u);
			resultmap.put("shopsInfo", shopsMap);
			resultmap.put("ossUrlInfo", ps);
			return resultmap;
		}	
	}
	@Override
	public Map<String, Object> updateUserHeadImage(User user) {
		Map<String,Object> map=new HashMap<String,Object>();
		int result=userDao.updateUserHeadImage(user);
		if(result==1){
			map.put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			map.put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
		}else{
			map.put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			map.put(Constants.MESSAGE, MessageDictionary.RESP_ERROR_MSG);
		}
		return map;
	}
	@Override
	public boolean updateUserDetailsInformations(User user) {
		int result=userDao.updateUserMoreInformation(user);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}
	@Override
	@Cacheable(value="common",key="'id_'+#id")
	public User selectByPrimaryKey(String id) {
		return userDao.getUserInfoByUserId(id);
	}
	@Override
	@CachePut(value="common",key="#user.getNickName()")
	public void insertSelective(User user) {
		userDao.insertUsersInfo(user);
	}
	@Override
	@CacheEvict(value="common",key="'id_'+#id")
	public void deleteByPrimaryKey(String id) {
		userDao.deleteByPrimaryKey(id);
	}

}
