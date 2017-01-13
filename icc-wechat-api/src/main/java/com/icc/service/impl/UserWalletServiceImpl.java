package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.UserWalletDao;
import com.icc.entity.UserWallet;
import com.icc.service.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {

	@Autowired
	private UserWalletDao userWalletDao;
	
	@Override
	public UserWallet getUserWalletInfo(String userId) {
		return userWalletDao.getUsersWalletInfo(userId);
	}

}
