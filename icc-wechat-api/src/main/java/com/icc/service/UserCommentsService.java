package com.icc.service;

import java.util.List;
import java.util.Map;

import com.icc.common.model.HeadMessageDto;
import com.icc.entity.Comments;

/**
 * 
 * @description 用户商铺评论
 * @author 朱润
 * @date 2016年9月23日上午11:26:19
 */
public interface UserCommentsService {

	//商铺评论列表
	public List<Map<String,Object>> queryComments(String shopsId);
	
	//添加评论
	public HeadMessageDto insertShopsComments(Comments com);
	
	public int deleteShopsComments(String shopsId,String userId);
	
	public int updateShopsComments();
}
