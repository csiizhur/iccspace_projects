package com.icc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icc.common.Constants;
import com.icc.dao.SysNoticeDao;
import com.icc.service.SysNoticeService;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {

	@Autowired
	private SysNoticeDao sysNoticeDao;
	@Override
	public PageInfo<Map<String, Object>> getSysNoticeList(Integer pageNo,Integer pageSize) {
		pageNo=pageNo==null?Constants.pageNum:pageNo;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Map<String,Object>> list=sysNoticeDao.querySysNoticeList();
		PageInfo<Map<String,Object>> page=new PageInfo<Map<String,Object>>(list);
		return page;
	}

}
