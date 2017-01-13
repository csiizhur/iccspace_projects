package services;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class AddressServiceTest {

	@Autowired
	private AddressService addressService;
	
	@Test
	public void testAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cityNo", "320500000000");
		map.put("areaNo", "320505000000");
		map=addressService.getAllDataByCityNo(map);
		System.err.println(map.get("city"));	
	}
}
