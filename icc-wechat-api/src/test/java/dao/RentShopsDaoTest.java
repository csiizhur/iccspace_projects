package dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.RentShopsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class RentShopsDaoTest {

	@Autowired
	private RentShopsDao rentShopsDao;
	@Test
	public void testQueryAllData(){
		Map<String,Object> map=new HashMap<String,Object>();
		//map.put("id", "dsfgg");
		rentShopsDao.insertRentShopsInfo(map);
	}
}
