package com.icc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.BusinessTypeDao;
import com.icc.entity.BusinessType;
import com.icc.service.BusinessTypeService;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

	@Autowired
	private BusinessTypeDao businessTypeDao;
	
	@Override
	public List<BusinessType> getAllBusinessTypeData() {
		return businessTypeDao.queryAllBusinessTypeData();
	}

}
