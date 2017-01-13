package com.icc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * @description 支付交易信息
 * @author zhur
 * @date 2016年9月13日上午9:44:54
 */
public class Transaction implements Serializable {

	private static final long serialVersionUID = 2898149279486956925L;

	private String Id;
	private String transNo;
	private String userId;
	private int transType;
	private int channel;
	private Timestamp transTime;
	private int transStatus;
	private BigDecimal transAmount;
	private String deleteUserId;
	private Timestamp deleteTime;
	private int deleted;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTransType() {
		return transType;
	}
	public void setTransType(int transType) {
		this.transType = transType;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public Timestamp getTransTime() {
		return transTime;
	}
	public void setTransTime(Timestamp transTime) {
		this.transTime = transTime;
	}
	public int getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(int transStatus) {
		this.transStatus = transStatus;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public String getDeleteUserId() {
		return deleteUserId;
	}
	public void setDeleteUserId(String deleteUserId) {
		this.deleteUserId = deleteUserId;
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
