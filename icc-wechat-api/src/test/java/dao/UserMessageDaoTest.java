package dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.UserDao;
import com.icc.dao.UserMessageDao;
import com.icc.entity.UserMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class UserMessageDaoTest {

	@Autowired
	private UserMessageDao userMessageDao;
	@Autowired
	private UserDao userDao;
	@Test
	public void testQueryCity(){
		
		userDao.queryUUID();
		//userMessageDao.insertUserMessage("sdf", "sf");
		UserMessage um=new UserMessage();
		um.setMsgContent("这是一条消息");
		userMessageDao.insertUserMessage(um);
	}
	@Test
	public void testProcedureQueryMessage(){
		
		List<Map<String,Object>> map=userMessageDao.queryUserMessageListByToUserId("528dbe0a8d0111e6be9a40f2e937fddf");
		System.err.println(map);
	}
	
}
