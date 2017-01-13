package com.icc.common;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.icc.util.DESPlus;

/**
 * 
 * @description db密码回调解密
 * @author zhur
 * @date 2016年8月30日下午4:01:12
 */
@SuppressWarnings("serial")
public class DBPasswordCallback extends DruidPasswordCallback {

	private static Logger log=LoggerFactory.getLogger(DBPasswordCallback.class);
	public void setProperties(Properties p) {
		super.setProperties(p);
		String pwd = p.getProperty("password");
		try {
			if (!StringUtils.isEmpty(pwd)) {
				String decrypt_pwd = new DESPlus().decrypt(pwd);
				setPassword(decrypt_pwd.toCharArray());
			}
			log.info(getPassword()+"this is dbpassword");
		} catch (Exception e) {
			setPassword(pwd.toCharArray());
			e.printStackTrace();
		}
	}

}
