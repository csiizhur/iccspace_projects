package com.icc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.BlackListDao;
import com.icc.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {

	@Autowired
	private BlackListDao blackListDao;
	@Override
	public int insertBlackListUsers(Map<String, Object> map) {
		return blackListDao.insertBlackListUsers(map);
	}

}
