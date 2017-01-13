package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description
 * @author zhurun
 * @date 2016年9月30日下午2:58:46
 */
public class EntityAccessLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 865527493522819086L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 操作者
	 */
	private String operator;
	/**
	 * 服务名称（DAO接口完整名）
	 */
	private String serviceName;
	/**
	 * 方法名称
	 */
	private String methodName;
	/**
	 * 参数内容
	 */
	private String paramsContent;
	/**
	 * 请求时间,格式：yyyy-MM-dd HH:mm:ss
	 */
	private java.util.Date operationTime;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
	public EntityAccessLog(){
	}

	public EntityAccessLog(
		java.lang.String id
	){
		this.id = id;
	}


	public String getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Long
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Long
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取操作者
	 * @return java.lang.String
	 */
	public String getOperator() {
		return this.operator;
	}
	
	/**
	 * 设置操作者
	 * @param operator
	 * @type java.lang.String
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * 获取服务名称（DAO接口完整名）
	 * @return java.lang.String
	 */
	public String getServiceName() {
		return this.serviceName;
	}
	
	/**
	 * 设置服务名称（DAO接口完整名）
	 * @param serviceName
	 * @type java.lang.String
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	/**
	 * 获取方法名称
	 * @return java.lang.String
	 */
	public String getMethodName() {
		return this.methodName;
	}
	
	/**
	 * 设置方法名称
	 * @param methodName
	 * @type java.lang.String
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	/**
	 * 获取参数内容
	 * @return java.lang.String
	 */
	public String getParamsContent() {
		return this.paramsContent;
	}
	
	/**
	 * 设置参数内容
	 * @param paramsContent
	 * @type java.lang.String
	 */
	public void setParamsContent(String paramsContent) {
		this.paramsContent = paramsContent;
	}
	
	/**
	 * 获取请求时间,格式：yyyy-MM-dd HH:mm:ss
	 * @return java.util.Date
	 */
	public java.util.Date getOperationTime() {
		return this.operationTime;
	}
	
	/**
	 * 设置请求时间,格式：yyyy-MM-dd HH:mm:ss
	 * @param operationTime
	 * @type java.util.Date
	 */
	public void setOperationTime(java.util.Date operationTime) {
		this.operationTime = operationTime;
	}
	
	/**
	 * 获取创建人
	 * @return java.lang.String
	 */
	public String getCreateBy() {
		return this.createBy;
	}
	
	/**
	 * 设置创建人
	 * @param createBy
	 * @type java.lang.String
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 获取更新人
	 * @return java.lang.String
	 */
	public String getUpdateBy() {
		return this.updateBy;
	}
	
	/**
	 * 设置更新人
	 * @param updateBy
	 * @type java.lang.String
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * 设置创建时间
	 * @param createTime
	 * @type java.util.Date
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取更新时间
	 * @return java.util.Date
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	/**
	 * 设置更新时间
	 * @param updateTime
	 * @type java.util.Date
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}