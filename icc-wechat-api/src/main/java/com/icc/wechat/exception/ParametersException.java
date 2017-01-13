package com.icc.wechat.exception;

import java.text.MessageFormat;

import com.icc.common.message.ResourceManager;

/**
 * 
 * @description 参数异常处理
 * @author zhurun
 * @date 2016年9月27日下午4:05:43
 */
public class ParametersException extends RuntimeException {

	private static final long serialVersionUID = -836390753377066248L;
	
	public static final ResourceManager MESSAGE = ResourceManager.getInstance("com.icc.common.message.message");
	private String resp_code;
	private String resp_message;
	public ParametersException(String resp_code, String resp_message) {
		//super(MESSAGE.getString(resp_message));
		super(resp_message);
		this.resp_code = resp_code;
		this.resp_message = resp_message;
	}
	public ParametersException(String resp_code) {
		//super(MESSAGE.getString(resp_code));
		super(resp_code);
	}
	public String getResp_code() {
		return resp_code;
	}
	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}
	public String getResp_message() {
		return resp_message;
	}
	public void setResp_message(String resp_message) {
		this.resp_message = resp_message;
	}
	
	public static void main(String[] args) {
		if(2==1){
			new ParametersException("001", "saf");
			
		}else{
			System.err.println(MessageFormat.format("sffc", "ff"));
			//throw new ParametersException("001", "测试异常");
			throw new ParametersException(MESSAGE.getFormattedString("error","这是"));
		}
	}
}
