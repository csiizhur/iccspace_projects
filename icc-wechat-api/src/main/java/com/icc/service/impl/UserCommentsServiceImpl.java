package com.icc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.CommentsDao;
import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsHistoryDao;
import com.icc.dao.UserDao;
import com.icc.entity.Comments;
import com.icc.entity.User;
import com.icc.service.UserCommentsService;
@Service
public class UserCommentsServiceImpl implements UserCommentsService {

	@Autowired
	private CommentsDao userCommentsDao;
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Autowired
	private RentShopsDao rentShopsDao;
	@Autowired
	private UserDao userDao;
	@Override
	public List<Map<String,Object>> queryComments(String shopsId) {
		List<Map<String,Object>> queryList=userCommentsDao.queryShopsCommentsByShopsId(shopsId);
		return queryList;
	}

	@Override
	public HeadMessageDto insertShopsComments(Comments com) {
		HeadMessageDto dto=new HeadMessageDto();
		int result=userCommentsDao.insertUserComments(com);
		//更新评论次数
		String userId=com.getUserId();
		User user=userDao.getUserInfoByUserId(userId);
		int updateFrequen=0;
		if(user.getUserRole()==Constants.RELEASE_SHOPS_TYPE_LEASE){
			updateFrequen=rentShopsDao.updateRentShopsCommentsFrequen(com.getShopsId());
		}
		if(user.getUserRole()==Constants.RELEASE_SHOPS_TYPE_RENT){
			updateFrequen=shopsHistoryDao.updateShopsHistoryCommentsFrequen(com.getShopsId());
		}
		if(result==Constants.AFFECTED_ROWS_1 && updateFrequen==Constants.AFFECTED_ROWS_1){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}else{
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}
		return dto;
	}

	@Override
	public int deleteShopsComments(String shopsId, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShopsComments() {
		// TODO Auto-generated method stub
		return 0;
	}

}
