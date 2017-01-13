package com.icc.aop.aspect;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icc.service.UserService;

public class AopTest {

	@Test
	public void testAOpAspectJ(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"/spring/spring-aop.xml");
		int t = 9;
		for (int i = 0; i < t; i++) {
			new Thread(new ThreadTest(ac)).start();
		}
	}
	
	@Test
	public void testAOP(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"/spring/spring-aop.xml");
		UserService vo = (UserService) ac.getBean("caulteService");
		vo.queryUUID();
	}
}

class ThreadTest implements Runnable {
	private ApplicationContext ac;

	public ThreadTest(ApplicationContext ac) {
		this.ac = ac;
	}
	public void run() {
		UserService vo = (UserService) ac.getBean("userService");
		vo.queryUUID();
	}
}
