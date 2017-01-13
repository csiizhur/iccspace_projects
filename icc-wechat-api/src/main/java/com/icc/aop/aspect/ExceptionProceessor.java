package com.icc.aop.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.ibatis.binding.BindingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.icc.wechat.exception.ParametersException;

/**
 * 
 * @description 异常通知
 * @author zhurun
 * @date 2016年9月30日上午9:33:56
 */
public class ExceptionProceessor {
	
	private Logger log = LoggerFactory.getLogger(ExceptionProceessor.class);
	private static String TARGET_METHOD_PROXY_NAME = "methodInvocation";

	public Object exceptionProceess(ProceedingJoinPoint pjp) {
		log.info("ExceptionProceessor begining ProceedingJoinPoint is " + pjp);
		Object returnObj = null;
		try {
			returnObj = pjp.proceed();
			return returnObj;
		} catch (Throwable e) {
			log.error("系统抛出异常，详细信息：" + e.getMessage());
			BaseEntity returnBe = null;
			try {
				Field f = (Field)pjp.getClass().getDeclaredField(TARGET_METHOD_PROXY_NAME);
				f.setAccessible(true);
				ReflectiveMethodInvocation rmi = (ReflectiveMethodInvocation)f.get(pjp);
				Method method = rmi.getMethod();
				method.setAccessible(true);
				Object returnObject = method.getReturnType().newInstance();
				if(returnObject instanceof BaseEntity){
					returnBe = (BaseEntity) returnObject;
				}
			} catch (SecurityException e1) {
				log.error("SecurityException,详细信息：" + e1.getMessage());
				genException(e);
			} catch (NoSuchFieldException e1) {
				log.error("NoSuchFieldException,详细信息：" + e1.getMessage());
				genException(e);
			} catch (IllegalAccessException ie){
				log.error("IllegalAccessException,详细信息：" + ie.getMessage());
				genException(e);
			} catch (InstantiationException ie){
				log.error("InstantiationException,详细信息：" + ie.getMessage());
				genException(e);
			} catch (DataIntegrityViolationException e2) {
				log.error("DataIntegrityViolationException,详细信息："+e2.getMessage());
				genException(e2);
			}
			if(returnBe != null){
				if(e instanceof NullPointerException){
					
					return returnBe;
				}else if(e instanceof ParametersException){
					ParametersException pe=(ParametersException)e;
					returnBe.setRespCode(pe.getResp_code());
					returnBe.setRespDesc(pe.getMessage());
					return returnBe;
				}
			}else{
				genException(e);
			}
		}
		return returnObj;
	}
	
	private void genException(Throwable e) {
		if(e instanceof NullPointerException){
			log.error("账户中心内部异常，详细信息：" + e.getMessage());
			throw new RuntimeException("系统内部异常，详细信息：" + e.getMessage());
		}else if(e instanceof ParametersException){
			log.error("请求参数错误" +e.getMessage());
			throw new RuntimeException("请求参数错误："+ e.getMessage());
		}else if(e instanceof BindingException){
			log.error("mapper 参数绑定错误：" + e.getMessage());
		}else if(e instanceof DataAccessException){
			throw new RuntimeException("数据库操作异常："+e.getMessage());
		}else if(e instanceof IllegalArgumentException){
			//java.lang.IllegalArgumentException: 用户编号不能为空
			throw new RuntimeException("参数错误"+e.getMessage());
		}
	}
}
