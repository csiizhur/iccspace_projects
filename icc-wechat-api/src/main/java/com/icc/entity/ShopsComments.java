package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @description 商铺评论信息类 
 * @author zhur
 * @date 2016年9月13日上午9:25:58
 */
public class ShopsComments implements Serializable {

	private static final long serialVersionUID = 1988230264433334401L;
	
	private String Id;
	private String shopsId;
	private String userId;
	private Timestamp commentsTime;
	private String commentsContent;
	private String replyUserId;
	private Timestamp deleteTime;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getCommentsTime() {
		return commentsTime;
	}
	public void setCommentsTime(Timestamp commentsTime) {
		this.commentsTime = commentsTime;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Timestamp getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	

}
