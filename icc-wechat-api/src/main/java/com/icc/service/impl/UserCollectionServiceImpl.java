package com.icc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.message.ResourceManager;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.UserCollectionDao;
import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.entity.UserCollection;
import com.icc.service.UserCollectionService;
import com.icc.wechat.exception.ParametersException;

@Service
public class UserCollectionServiceImpl implements UserCollectionService{

	private ResourceManager rm=ResourceManager.getInstance("com.icc.common.message.message");
	@Autowired
	private UserCollectionDao userCollectionDao;
	@Autowired
	private UserDao userDao;
	@Override
	public PageInfo<UserCollection> getUsersCollectionShopsList(String userId,Integer pageNo,Integer pageSize) {
		
		pageNo=pageNo==null?Constants.pageNum:pageNo;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<UserCollection> list=userCollectionDao.getUsersCollectionShopsListForPage(userId);
		PageInfo<UserCollection> page=new PageInfo<UserCollection>(list);
		return page;
	}
	@Override
	public List<Map<String,Object>> getUsersCollectionShopsList(String userId) {
		List<Map<String,Object>> list=new ArrayList<>();
		if(StringUtils.isNotEmpty(userId)){
			User user=userDao.getUserInfoByUserId(userId);
			int role=user.getUserRole();
			
			if(Constants.USER_ROLE_LEASE==role){
				list=userCollectionDao.getUsersCollectionRentShops(userId);
			}
			if(Constants.USER_ROLE_RENT==role){
				list=userCollectionDao.getUsersCollectionShopsList(userId);
			}
		}
		return list;
	}
	@Override
	public int insertCollectionShopsByUser(String userId, String shopsId) {
		int result=0;
		//是否收藏过
		UserCollection uc=userCollectionDao.queryIsCollectionShopsByUser(userId, shopsId);
		//获取用户角色
		User user=userDao.getUserInfoByUserId(userId);
		if(uc==null){
			result= userCollectionDao.insertCollectionShopsByUser(userId, shopsId);
			if(user!=null){
				int uRole=user.getUserRole();
				if(uRole==Constants.USER_ROLE_LEASE){
					//更新收藏次数(+1)
					userCollectionDao.updateRentShopsCollectionFrequen(shopsId);
				}else{
					userCollectionDao.updateShopsHistoryCollectionFrequen(shopsId);
				}
			}
		}else{
			result=userCollectionDao.updateOneCollectionShopsByUser(userId, shopsId);
			if(user!=null){
				int uRole=user.getUserRole();
				if(uRole==Constants.USER_ROLE_LEASE){
					//更新收藏次数(+1)
					userCollectionDao.updateRentShopsCollectionFrequen(shopsId);
				}else{
					userCollectionDao.updateShopsHistoryCollectionFrequen(shopsId);
				}
			}
		}
		return result;
	}
	@Override
	public int deleteCollectionShopsByUser(String deleteUserId,List<String> Ids) {
		return userCollectionDao.batchDeleteCollectionShopsByUser(Ids,deleteUserId);
	}
	@Override
	public HeadMessageDto deleteOneCollectionShopsByUser(String shopsId, String userId) {
		HeadMessageDto dto=new HeadMessageDto();
		if(StringUtils.isNotEmpty(userId)&&StringUtils.isNotEmpty(shopsId)){
			int result=userCollectionDao.deleteOneCollectionShopsByUser(userId, userId, shopsId);
			//这里更新收藏次数
			User user=userDao.getUserInfoByUserId(userId);
			int uRole=user.getUserRole();
			if(uRole==Constants.USER_ROLE_LEASE){
				//更新收藏次数(-1)
				userCollectionDao.updateRentShopsCollectionFrequen2(shopsId);
			}else{
				userCollectionDao.updateShopsHistoryCollectionFrequen2(shopsId);
			}
			if(result==Constants.AFFECTED_ROWS_1){	
				dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				dto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			}else{
				dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				dto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			}
			
		}else{
			throw new ParametersException(rm.getFormattedString("userId_shopsId_is_not_null", userId,shopsId));
		}
		return dto;
	}

}
