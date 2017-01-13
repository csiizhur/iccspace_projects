package com.icc.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icc.service.TestAspectJService;

public class Main {
public static void main(String[] args) {
	//创建可spring IOC容器
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/aspectj-xml.xml");
	//从IOC容器获取bean实例
	TestAspectJService arithmeticCalculator =  applicationContext.getBean(TestAspectJService.class);
	int result = arithmeticCalculator.add(4, 6);
	System.out.println(result);
	result = arithmeticCalculator.sub(4, 6);
	System.out.println(result);
	System.out.println(result);
	result = arithmeticCalculator.mul(4, 6);
	System.out.println(result);
	System.out.println(result);
	result = arithmeticCalculator.div(4, 0);
	System.out.println(result);
}
}
