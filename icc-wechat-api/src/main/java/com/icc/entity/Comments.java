package com.icc.entity;

import java.io.Serializable;

public class Comments implements Serializable {

	private static final long serialVersionUID = 6845039799872878160L;

	private String CommentsId;
	private String shopsId;
	private String UserId;
	private String commentsContent;
	private String replyCommentsId;
	public String getCommentsId() {
		return CommentsId;
	}
	public void setCommentsId(String commentsId) {
		CommentsId = commentsId;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getReplyCommentsId() {
		return replyCommentsId;
	}
	public void setReplyCommentsId(String replyCommentsId) {
		this.replyCommentsId = replyCommentsId;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
}
