package com.icc.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	
	public void beforeMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object>args = Arrays.asList(joinpoint.getArgs());
		System.out.println("前置通知：The method "+ methodName +" begins with " + args);
	}
	
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("后置通知：The method "+ methodName +" ends ");
	}
	
	public void afterReturnning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("返回通知：The method "+ methodName +" ends with " + result);
	}
	
	public void afterThrowing(JoinPoint joinpoint, Exception e) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("异常通知：The method "+ methodName +" occurs exception " + e);
	}
	
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result = null;
		String methodName = point.getSignature().getName();
		try {
			//前置通知
			System.out.println("The method "+ methodName +" begins with " + Arrays.asList(point.getArgs()));
			//执行目标方法
			result = point.proceed();
			//翻译通知
			System.out.println("The method "+ methodName +" ends with " + result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method "+ methodName +" occurs exception " + e);
			throw new RuntimeException(e);
		}
		//后置通知
		System.out.println("The method "+ methodName +" ends");
		return result;
	}
}
