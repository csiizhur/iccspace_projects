package com.icc.common;

import java.util.UUID;

/**
 * 
 * @description 主键工厂
 * @author zhur
 * @date 2016年9月23日上午11:53:21
 */
public class IdFactory {

	/**
	 * 获取uuid并且去掉-
	 * @return
	 */
	public String getUUID(){
		UUID uuid=UUID.randomUUID();
		String replUUID=uuid.toString().replaceAll("-", "");
		return replUUID;
	}
}
