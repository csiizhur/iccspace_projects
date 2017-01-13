package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @description 客户投诉信息类 
 * @author zhur
 * @date 2016年9月13日上午9:10:58
 */
public class Complaints implements Serializable {

	private static final long serialVersionUID = -896246847907868867L;

	private String Id;
	private String linkShopsId;
	private String complaintUserId;
	private Timestamp complaintTime;
	private int complaintType;
	private int complaintStatus;
	private String complaintResult;
	private Timestamp resultTime;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getLinkShopsId() {
		return linkShopsId;
	}
	public void setLinkShopsId(String linkShopsId) {
		this.linkShopsId = linkShopsId;
	}
	public String getComplaintUserId() {
		return complaintUserId;
	}
	public void setComplaintUserId(String complaintUserId) {
		this.complaintUserId = complaintUserId;
	}
	public Timestamp getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Timestamp complaintTime) {
		this.complaintTime = complaintTime;
	}
	public int getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(int complaintType) {
		this.complaintType = complaintType;
	}
	public int getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(int complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public String getComplaintResult() {
		return complaintResult;
	}
	public void setComplaintResult(String complaintResult) {
		this.complaintResult = complaintResult;
	}
	public Timestamp getResultTime() {
		return resultTime;
	}
	public void setResultTime(Timestamp resultTime) {
		this.resultTime = resultTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
}
