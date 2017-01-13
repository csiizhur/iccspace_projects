package com.icc.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface SysNoticeService {

	PageInfo<Map<String,Object>> getSysNoticeList(Integer pageNo,Integer pageSize);
}
