package dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.ShopsDao;
import com.icc.dao.ShopsHistoryDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class ShopsDaoTest {

	@Autowired
	private ShopsDao shopsDao;
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Test
	public void testQueryAllData(){
		
		String userId="528dbe0a8d0111e6be9a40f2e937fddf";
		List<Map<String,Object>> l=shopsDao.queryShopInformationByUserId(userId);
		for(Map m:l){
			System.err.println(m.get("releaseType"));
			System.err.println(m.get("shopsId"));
		}
	}
	
	@Test
	public void testDelete(){
		String userId="528dbe0a8d0111e6be9a40f2e937fddf";
		String shopsId="48282fcdb2ef49f38f933ab548ee725f";
		shopsDao.deleteReleaseShopsByShopsIdForUser(userId, shopsId);
	}
	@Test
	public void testFilter(){
		List<String> listAreaNo=null;
		List<String> estatesTypes=null;
		double shopMinSize=10;
		double shopMaxSize=120;
		BigDecimal minRentFee=new BigDecimal(11);
		BigDecimal maxRentFee=new BigDecimal(111);
		List<Map<Object,Object>> l=shopsDao.queryNewShopsList(listAreaNo, estatesTypes, shopMinSize, shopMaxSize,minRentFee,maxRentFee);
		System.err.println(l);
	}
	/**
	 * 影响行数0
	 */
	@Test
	public void testBaseShopUpdate(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("historyId", "we");
		map.put("addressUnique", "苏州市吴中区null金鸡湖大道");
		map.put("baseShopsId", "79b09aa647eb49a5a29663476e7adaf0");
		int resultBase=shopsDao.updateBaseRentShopInformationRelease(map);
		//log.info("影响行数："+resultBase);
		System.err.println(resultBase+"==========");
	}
	//shopsId,baseShopsId,address,shopSize,lat,lng,
	@Test
	public void testShopUpdate(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityNo", "we");
		map.put("areaNo", "ew");
		map.put("shopsId", "f3281909c7eb4b1e9dce2542c0a374a9");
		map.put("baseShopsId", "00e2b32321ea4fb2b8ed85a3f9986efb");
		map.put("address", "金鸡湖大道东");
		map.put("shopSize", 2.36);
		map.put("lat", "1.32");
		map.put("lng", "1.33");
		int resultBase=shopsHistoryDao.updateBaseRentShopHistoryId(map);
		//log.info("影响行数："+resultBase);
		System.err.println(resultBase+"==========");
	}
	
}
