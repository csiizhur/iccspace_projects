package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 委托租赁信息类 
 * @author zhur
 * @date 2016年9月13日上午9:15:44
 */
public class EntrustLease implements Serializable {
	private static final long serialVersionUID = -5772555814082882500L;

	//private static final long serialVersionUID = -896246847907868867L;
	private String Id;
	private String userId;
	private String agentUserId;
	private String entrustUserId;
	private String shopsId;
	private int agentSchedule;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAgentUserId() {
		return agentUserId;
	}
	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}
	public String getEntrustUserId() {
		return entrustUserId;
	}
	public void setEntrustUserId(String entrustUserId) {
		this.entrustUserId = entrustUserId;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public int getAgentSchedule() {
		return agentSchedule;
	}
	public void setAgentSchedule(int agentSchedule) {
		this.agentSchedule = agentSchedule;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
}
