package com.icc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @description 工商币交易记录 
 * @author zhur
 * @date 2016年9月13日上午9:19:44
 */
public class ICCBTransaction implements Serializable {

	private static final long serialVersionUID = -3504740816110452868L;

	private String Id;
	private String transNo;
	private String walletId;
	private int transType;
	private Timestamp transTime;
	private int transStatus;
	private int transAmount;
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
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public int getTransType() {
		return transType;
	}
	public void setTransType(int transType) {
		this.transType = transType;
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
	public int getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(int transAmount) {
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
