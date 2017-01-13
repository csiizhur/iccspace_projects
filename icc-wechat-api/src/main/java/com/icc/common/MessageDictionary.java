package com.icc.common;

/**
 * 
 * @description 信息码字典
 * @author zhurun
 * @date 2016年9月28日上午9:30:08
 */
public class MessageDictionary {

	public static final String RESP_SUCCESS_CODE="0000";//返回正常数据
	public static final String RESP_NODATA_CODE="0001";//返回数据为空
	public static final String RESP_ERROR_CODE="0002";//返回异常
	public static final String AUTHCODE_INVALID_CODE="0003";//code不可用 重新获取code
	public static final String OPENID_AVAILABLE_CODE="0004";//OPENID获取成功
	public static final String OPENID_IN_SESSION_CODE="0005";//openid存在session中
	public static final String RESP_NOUSER_CODE="0006";//用户身份不存在
	public static final String SESSION_USER_INVALID_CODE="0007";//USER SESSION
	public static final String FILE_NOT_FIND_CODE="0008";
	public static final String RESP_NO_USERID_CODE="0009";
	public static final String RESP_BAIDU_NODATA_CODE="0010";
	public static final String RESP_EXIST_ENTRUST_CODE="0011";
	
	public static final String RESP_QRY_MSG="query data";
	public static final String RESP_UPDATE_MSG="update data";
	public static final String RESP_SUCCESS_UPDATE_MSG="update data success";
	public static final String RESP_INSERT_MSG="insert data";
	public static final String RESP_SUCCESS_INSERT_MSG="insert data success";
	public static final String RESP_DELETE_MSG="delete data";
	public static final String RESP_ERROR_MSG="error data";
	public static final String RESP_ERROR_INSERT_MSG="insert data fail";
	public static final String RESP_ERROR_UPDATE_MSG="update data fail";
	public static final String RESP_DATAISNULL_MSG="data is null";
	public static final String AUTHCODE_INVALID_MESSAGE="auth code is invalid";
	public static final String OPENID_AVAILABLE_MESSAGE="get openid success";
	public static final String OPENID_IN_SESSION_MESSAGE="openid is in session";
	public static final String RESP_NOUSER_MSG="user invild";
	public static final String SESSION_USER_INVALID_MESSAGE="user is not in session";
	public static final String FILE_NOT_FIND="file not find";
	public static final String RESP_USERID_IS_NOTNULL_MSG="userId is not null";
	public static final String RESP_BAIDU_NODATA_MSG="from baidu is no data";
	public static final String RESP_EXIST_ENTRUST_MSG="shops is exist entrust";
}
