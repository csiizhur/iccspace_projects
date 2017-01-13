package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.ShopsDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.entity.ShopsPhotosInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class ShopsPhotosDaoTest {

	@Autowired
	private ShopsPhotosInfoDao photosDao;
	@Autowired
	private ShopsDao shopsDao;
	@Test
	public void testQueryPhotosList(){
		
		String shopsId="a9da74acd137480ea846c07614a857dd";
		List<ShopsPhotosInfo> list=photosDao.queryShopsPhotosListByShopsId(shopsId);
		for(ShopsPhotosInfo p:list){
			System.err.println(p.getOssUrl());
		}
	}
	@Test
	public void testQueryPhotosList1(){
		
		String shopsId="7b0be3ed916311e6bedf507b9d158519";
		Map<String,Object> l=shopsDao.queryShopsInfoById(shopsId);
		
	}
	
	@Test
	public void testBatchUpdate(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map m=new HashMap<String,Object>();
		m.put("id", "sf");
		m.put("imageURL1", "sdffg");
		list.add(m);
		photosDao.batchInsertPhotos(list);
	}
}
