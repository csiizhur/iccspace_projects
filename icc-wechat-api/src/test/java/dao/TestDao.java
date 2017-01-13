package dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.ShopsHistoryDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.entity.ShopsHistory;
import com.icc.entity.ShopsPhotosInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class TestDao {

	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Resource
	private ShopsPhotosInfoDao photosDao;
	/**
	 * 乐观锁
	 */
	@Test
	public void testUpdate(){
		
		String shopsId_1="0ba671d6cdfc4b109cf425ddddff3a43";
		//根据相同的id查询出商铺信息，赋给2个map
		ShopsHistory m1=shopsHistoryDao.queryShopsHistoryInfo(shopsId_1);
		ShopsHistory m2=shopsHistoryDao.queryShopsHistoryInfo(shopsId_1);
		
		m1.setUserClick(11);;
		shopsHistoryDao.updateForOptimisticLocking(m1.getUserClick(),m1.getId(),m1.getVersion());
		m2.setUserClick(22);;
		//这里的version还是1，而数据库已经version+1故更新失败
		shopsHistoryDao.updateForOptimisticLocking(m2.getUserClick(),m2.getId(),m2.getVersion());
	}
	
	/**
	 * 批量插入
	 */
	@Test
	public void testBatchInsert(){
		List<ShopsPhotosInfo> list=new ArrayList<ShopsPhotosInfo>();
        /*for (int i = 0; i < 1000; i++) {
        	ShopsPhotosInfo n = new ShopsPhotosInfo("wer","123");
            list.add(n);
        }*/
		ShopsPhotosInfo n = new ShopsPhotosInfo("wer","123");
        list.add(n);
        ShopsPhotosInfo n1 = new ShopsPhotosInfo("wer1","1232");
        list.add(n1);
		photosDao.batchInsertPhotos(list);
	}
	/**
	 * 多表关联查询
	 */
	@Test
	public void testQueryOne2Many(){
		List<ShopsHistory> l=photosDao.queryShopAndPhotos("7b0be3ed916311e6bedf507b9d158519");
		System.err.println(l);
		List<ShopsHistory> l2=photosDao.queryShopAndPhotos2("7b0be3ed916311e6bedf507b9d158519");
		System.err.println(l2);
	}
}
