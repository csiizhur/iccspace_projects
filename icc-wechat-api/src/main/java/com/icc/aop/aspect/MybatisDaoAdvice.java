package com.icc.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class MybatisDaoAdvice {

	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println(pjp.getTarget());
//		System.out.println(pjp.getThis());
//		System.out.println(pjp.getSignature());
//		System.out.println(pjp.getArgs());
		String methodSignature = pjp.getSignature().toString();
		Object returnObj = pjp.proceed();
		//需要使用乐观锁更新使用此方法--updateForOptimisticLocking
		//暂时没做
		if (methodSignature.lastIndexOf("updateForOptimisticLocking") != -1) {
			if ((Integer) returnObj < 1) {
				throw new ObjectOptimisticLockingFailureException(pjp.getSignature().getClass(), methodSignature);
			}
		}
		return returnObj;
	}
}
