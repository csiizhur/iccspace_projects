package com.icc.aop.aspect;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
/**
 * 
 * @description 切面类
 * @author zhurun
 * @date 2016年9月28日下午5:00:28
 */
public class CaulteTimesAspect implements MethodBeforeAdvice, AfterReturningAdvice {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	private ThreadLocal<Long> tl=new ThreadLocal<>();
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {

		//从ThreadLocal中get值并计算
		long runTime=System.currentTimeMillis()-tl.get();
		log.info(MessageFormat.format("{0}>>>本次耗时>>>{1}ms", Thread.currentThread().getName(),runTime));
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {

		//获取当前时间，set到ThreadLocal中
		tl.set(System.currentTimeMillis());

	}

	public void afterThrowing(Method method, Object[] objArr, Object target,
			Exception ex) throws Throwable {
		log.error("err>>>"+ex);
	}
}
