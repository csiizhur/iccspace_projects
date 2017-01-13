package com.icc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.TextMessageDao;
import com.icc.service.TextMessageService;

@Service
public class TextMessageServiceImpl implements TextMessageService {

	@Autowired
	private TextMessageDao textMessageDao;
	@Override
	public String queryKeyWordForReply(Map<String, Object> map) {
		return textMessageDao.queryKeyWordForReply(map);
	}

}
