package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.UserDao;
import com.icc.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	@Test
	public void testQueryUserData(){
		
		User c=userDao.getUserInfoByUserId("a83be098a25211e6b0d000163e020977");

	}
	
}
