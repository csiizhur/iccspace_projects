package com.icc.entity;

import java.io.Serializable;
/**
 * 用户留言
 * @description
 * @author zhurun
 * @date 2016年11月17日上午10:18:24
 */
public class UserLeaveMessage implements Serializable {

	private static final long serialVersionUID = -5141385326002292013L;
	private String leaveMsgId;
	private String fromUserId;
	private String msgContent;
	public String getLeaveMsgId() {
		return leaveMsgId;
	}
	public void setLeaveMsgId(String leaveMsgId) {
		this.leaveMsgId = leaveMsgId;
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
}
