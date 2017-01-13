package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/spring-quartz.xml")
public class TestQuartz {

	@Test
	public void testJob(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
		
		ctx.getBean("startQuartz");
				
	}
}
