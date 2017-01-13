package com.icc.aop.aspect;

import java.lang.reflect.Method;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsHistoryDao;
import com.icc.util.StrUtil;

/**
 * 
 * @description 商铺操作记录
 * @author zhurun
 * @date 2016年9月29日下午1:40:03
 */
public class ShopsUserClickAdvice implements AfterReturningAdvice{

	private static final String recordUserClickMethod="queryShopsInfoById";
	
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Autowired
	private RentShopsDao rentShopsDao;
	public void doAfterRecordLog(JoinPoint jp){
		String methodName=jp.getSignature().getName();
		Object []args=jp.getArgs();
		
		if(StrUtil.isValidity(methodName)&& (methodName.startsWith("create") || methodName.startsWith("create")))
			return;
		Class [] interfacterClasses=jp.getTarget().getClass().getInterfaces();
		if(interfacterClasses!=null && interfacterClasses.length>0){
			String interfacterName=interfacterClasses[0].getName();
		}
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		
		log.error(returnValue+"---------");
		log.error(method.getName());
		
		//商铺详情 userClick+1
		if(method.getName().equals(recordUserClickMethod)){
			Map map = (Map) returnValue;
			if (map!=null) {
				int userClick = (int) map.get("userClick") + 1;
				String shopsId = (String) map.get("shopsId");
				int version = (int) map.get("version");
				shopsHistoryDao.updateForOptimisticLocking(userClick, shopsId, version);
			}
			log.error("更新click==完毕");
		}else if(method.getName().equals("getTenantUsersInfoById")){
			Map map=(Map)returnValue;
			//{oosUrlInfo=[], userInfo=com.icc.entity.User@1269541, shopsInfo=null}
			Map<String,Object> shopsInfo=(Map<String, Object>) map.get("shopsInfo");
			if (shopsInfo!=null) {
				int userClick;
				if (shopsInfo.get("userClick") == null) {
					userClick = 0;
				} else {
					userClick = (int) shopsInfo.get("userClick") + 1;
				}
				String shopsId = (String) shopsInfo.get("rentId");
				int version = (int) shopsInfo.get("version");
				log.error("更新rent_shop_click==完毕");
				rentShopsDao.updateForOptimisticLocking(userClick, shopsId, version);
			}
		}
	}
}
