package com.icc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.PropertysDao;
import com.icc.service.PropertysService;
@Service
public class PropertysServiceImpl implements PropertysService {

	@Autowired
	private PropertysDao propertysDao;
	@Override
	public Map<String, Object> queryPropertysInfoByShopsId(String shopsId) {
		return propertysDao.queryPropertyInfoForShopsId(shopsId);
	}

}
