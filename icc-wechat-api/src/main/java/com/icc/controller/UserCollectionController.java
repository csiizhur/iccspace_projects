package com.icc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.entity.BaseEntity;
import com.icc.entity.UserCollection;
import com.icc.entity.UserSessionDto;
import com.icc.service.UserCollectionService;

@Controller
@RequestMapping("/user_collection")
public class UserCollectionController  {

	@Autowired
	private UserCollectionService userCollectionService;

	/**
	 * 基于分页实现 
	 * 我的收藏出租商铺
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getUsersCollectionHistoryShops")
	public @ResponseBody String queryUsersCollectionShopsList(@RequestParam String userId,HttpServletRequest req) {
		BaseEntity be=new BaseEntity();
		
			List<Map<String,Object>> collectionList=userCollectionService.getUsersCollectionShopsList(userId);
			if (collectionList.size() > 0) {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
				be.setBody(collectionList);
			} else {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
			}
		
		HttpSession s = req.getSession();
		UserSessionDto u = (UserSessionDto) s.getAttribute(Constants.SESSION_USER);
		if (u != null) {
			userId = u.getUserId();	
		} else {
		}
		return JSONObject.toJSONString(be);

	}
	/**
	 * 我的收藏求租
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getUsersCollectionRentShops")
	public @ResponseBody JsonResult<HeadMessageDto> queryUsersCollectionRentShopsList(@RequestParam String userId){
		HeadMessageDto dto=new HeadMessageDto();
		
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		
		List<Map<String,Object>> list=userCollectionService.getUsersCollectionShopsList(userId);
		if(list.size()>0){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_QRY_MSG);
			jr.setBody(list);
		}else{
			dto.setCode(MessageDictionary.RESP_NODATA_CODE);
			dto.setMessage(MessageDictionary.RESP_DATAISNULL_MSG);
		}
		jr.setHead(dto);
		return jr;
	}
	/**
	 * 收藏商铺
	 * @param userId
	 * @param shopsId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/insertCollectionShopsForUser")
	public @ResponseBody String CollectionShopsByUser(String userId,String shopsId){
		BaseEntity be=new BaseEntity();
		int result=userCollectionService.insertCollectionShopsByUser(userId, shopsId);
		if(result==1){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	
	/**
	 * 收藏批量删除
	 * @param deleteUserId
	 * @param collectionIds
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/deleteCollectionShopsForUser")
	public @ResponseBody String CollectionShopsByUserDelete(String deleteUserId,@RequestParam List<String> collectionIds){
		BaseEntity be=new BaseEntity();
		int result=userCollectionService.deleteCollectionShopsByUser(deleteUserId, collectionIds);
		if(result>=1){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DELETE_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 收藏单个删除
	 * @param shopsId
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/deleteOneCollectionShopsForUser")
	public @ResponseBody JsonResult<HeadMessageDto> ColledtionShopsByUserDeleteOne(String shopsId,String userId){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		HeadMessageDto dto=userCollectionService.deleteOneCollectionShopsByUser(shopsId, userId);
		result.setHead(dto);
		return result;
	}
}
