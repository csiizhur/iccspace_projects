package com.icc.aop.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import com.icc.dao.UserAccessShopsDao;
import com.icc.dto.UserShopsDto;
import com.icc.entity.UserAccessShops;
/**
 * 记录谁看过我
 * @description
 * @author zhurun
 * @date 2016年11月25日下午5:12:25
 */
public class UserAccessShopsAdvice implements MethodBeforeAdvice{

	private static final String recordUserHistoryShopClickMethod="queryShopsInfoById";
	private static final String recordUserRentShopClickMethod="getTenantUsersInfoById";
	
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private UserAccessShopsDao userAccessShopsDao;

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		if(method.getName().equals(recordUserHistoryShopClickMethod)){
			UserAccessShops uas=new UserAccessShops();
			uas.setShopsId((String)args[0]);
			uas.setAccessUserId((String)args[1]);
			
			//userId存在时记录访问情况
			if (StringUtils.isNotEmpty((String)args[1])) {
				UserAccessShops access = userAccessShopsDao.queryUserAccessShops(uas);
				if (access != null) {
					long d = System.currentTimeMillis();
					Timestamp accessTime = access.getAccessTime();
					long accessTime2 = accessTime.getTime();
					if ((d - accessTime2) / 1000 / 60 / 60 / 24 >= 1) {
						userAccessShopsDao.updateForOptimisticLocking(access);
					}
				} else {
					userAccessShopsDao.insertUserAccessShops(uas);
				} 
			}
		}else if(method.getName().equals(recordUserRentShopClickMethod)){
			UserAccessShops uas=new UserAccessShops();
			UserShopsDto usdto=(UserShopsDto) args[0];
			uas.setAccessUserId(usdto.getCurrentUserId());
			uas.setShopsId(usdto.getShopsId());
			uas.setUserId(usdto.getRentUserId());
			//currentUserId存在时记录访问数据
			if (StringUtils.isNotEmpty(usdto.getCurrentUserId())) {
				UserAccessShops access = userAccessShopsDao.queryUserAccessShops(uas);
				if (access != null) {
					long d = System.currentTimeMillis();
					Timestamp accessTime = access.getAccessTime();
					long accessTime2 = accessTime.getTime();
					//只记录一天内最开始访问的记录
					if ((d - accessTime2) / 1000 / 60 / 60 / 24 >= 1) {
						userAccessShopsDao.updateForOptimisticLocking(access);
					}
				} else {
					userAccessShopsDao.insertUserAccessShops(uas);
				} 
			}
		}
		
	}
}
