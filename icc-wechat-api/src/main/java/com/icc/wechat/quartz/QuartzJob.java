package com.icc.wechat.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icc.common.WechatTask;

/**
 * 
 * @description 定时获取access_token
 * @author zhur
 * @date 2016年9月18日下午2:09:00
 */
public class QuartzJob {

	protected final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void getTokenByJob(){
		
		try{
			WechatTask timer=new WechatTask();
			timer.getToken();
		}catch(Exception e){
			
		}
	}
}
