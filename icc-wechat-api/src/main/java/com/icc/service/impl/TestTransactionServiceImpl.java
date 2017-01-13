package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.service.TestTransactionService;

@Service
public class TestTransactionServiceImpl implements TestTransactionService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void testTransaction() {
		// TODO Auto-generated method stub
		userDao.insertUsersInfo(new User("","wer"));
		userDao.insertUsersInfo(new User("","werdfhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"));
	}

}
