package com.icc.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.icc.util.GlobalConstantsPropertiesUtil;

/**
 * 
 * @description 项目启动初始化方法
 * @author zhur
 * @date 2016年9月14日下午5:56:45
 */
public class InterfaceUrlInit {

	public synchronized static void init(){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		if(GlobalConstantsPropertiesUtil.interfaceUrlProperties==null){
			GlobalConstantsPropertiesUtil.interfaceUrlProperties = new Properties();
		}
		InputStream in = null;
		try {
			in = cl.getResourceAsStream("/config/url.properties");
			props.load(in);
			for(Object key : props.keySet()){
				GlobalConstantsPropertiesUtil.interfaceUrlProperties.put(key, props.get(key));
			}
			
			props = new Properties();
			in = cl.getResourceAsStream("/config/wechat.properties");
			props.load(in);
			for(Object key : props.keySet()){
				GlobalConstantsPropertiesUtil.interfaceUrlProperties.put(key, props.get(key));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}

}