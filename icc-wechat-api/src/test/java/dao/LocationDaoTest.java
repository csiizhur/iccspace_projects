package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.icc.dao.ShopsHistoryDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class LocationDaoTest {

	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Test
	public void testQueryAllData(){
		
		shopsHistoryDao.queryShopsLocationList("","","","","");
	}
	
}
