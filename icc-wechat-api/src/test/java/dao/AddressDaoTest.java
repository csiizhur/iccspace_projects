package dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.AddressDao;
import com.icc.entity.AreaInfo;
import com.icc.entity.CityInfo;
import com.icc.entity.StreetInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class AddressDaoTest {

	@Autowired
	private AddressDao addressDao;
	@Test
	public void testQueryAllData(){
		
		String cityNo="320500000000";
		CityInfo c=addressDao.queryAllDataByCityNo(cityNo);
		List<AreaInfo> a=c.getArea();
		for(AreaInfo as:a){
			
			System.err.println(as.getAreaName());
			List<StreetInfo> s=as.getStreet();
			for(StreetInfo si:s){
				System.err.println(as.getAreaName()+"===>"+si.getStreetName());
			}
		}
	}
	@Test
	public void testQueryAreaData(){
		String cityNo="320500000000";
		CityInfo c=addressDao.queryCityAndAreaByCityNo(cityNo);
		List<AreaInfo> a=c.getArea();
		for(AreaInfo as:a){
			System.err.println(as.getAreaName());
		}
	}
	@Test
	public void testQueryStreetData(){
		String areaNo="320505000000";
		AreaInfo a=addressDao.queryAreaAndStreetByAreaNo(areaNo);
		List<StreetInfo> l=a.getStreet();
		for(StreetInfo si:l){
			System.err.println(si.getStreetName());
		}
	}
	@Test
	public void testQueryCity(){
		System.err.println(addressDao.queryCityInfo());
		System.err.println(addressDao.queryAreaInfo());
	}
	@Test
	public void testQueryCityArea(){
		/*List<CityInfo> c=addressDao.queryAllDataByCityNo2();
		for(CityInfo ci:c){
			List<AreaInfo> a= ci.getArea();
			for(AreaInfo ai:a){
				System.err.println(ai.getAreaName());
			}
		}*/
	}
	
}
