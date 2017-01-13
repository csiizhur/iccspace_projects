package com.icc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsHistoryDao;
import com.icc.dao.UserAccessShopsDao;
import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.service.UserAccessShopsService;
@Service
public class UserAccessShopsServiceImpl implements UserAccessShopsService {

	@Autowired
	private UserAccessShopsDao userAccessShopsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Autowired
	private RentShopsDao rentShopsDao;
	@Override
	public List<Map<String, Object>> getUserAccessIn30Days(String userId) {
		//TODO 在shops_history Advice中无法通过参数获取到userID
		User user=userDao.getUserInfoByUserId(userId);
		List<String> Ids=new ArrayList<String>();
		if(Constants.USER_ROLE_LEASE==user.getUserRole()){
			Ids=rentShopsDao.queryRentIdsForUserId(userId);
		}
		if(Constants.USER_ROLE_RENT==user.getUserRole()){
			Ids=shopsHistoryDao.queryShopsIdsForUserId(userId);
		}
		List<Map<String,Object>> accessList=userAccessShopsDao.queryUsersAccessIn30Days(Ids);
		return accessList;
	}

}
