package com.icc.dao;

import java.util.List;
import java.util.Map;

import com.icc.entity.Comments;

public interface CommentsDao {

	//添加评论
	public int insertUserComments(Comments com);
	//评论列表
	public List<Map<String,Object>> queryShopsCommentsByShopsId(String shopsId);
}
