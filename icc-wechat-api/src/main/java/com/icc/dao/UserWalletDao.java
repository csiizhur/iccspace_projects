package com.icc.dao;

import com.icc.entity.UserWallet;

public interface UserWalletDao {

	//用户钱包查询
	UserWallet getUsersWalletInfo(String userId);
	
}
