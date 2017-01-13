package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.icc.baidu_data_api.controller.PlaceApiController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring.xml")
public class ApplicationContextTest {

	@Test
	public void testBean(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:spring.xml");
		RestTemplate rs=(RestTemplate) ac.getBean("restTemplate");
		PlaceApiController pc=(PlaceApiController) ac.getBean("placeApiController");
	}
}
