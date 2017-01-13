package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.dao.UserCollectionDao;
import com.icc.entity.UserCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class UserCollectionDaoTest {

	@Autowired
	private UserCollectionDao collectionDao;
	
	
	/**
	 * 批量更新
	 * 修改的字段值都是一样的。
	 */
	@Test
	public void testBatchInsert(){
		List<UserCollection> collectionIds=new ArrayList<UserCollection>();
     
		UserCollection n = new UserCollection();
		n.setId("1");
		n.setDeleteUserId("528dbe0a8d0111e6be9a40f2e937fddf");
		collectionIds.add(n);
        UserCollection n1 = new UserCollection();
        n1.setId("2");
        n1.setDeleteUserId("528dbe0a8d0111e6be9a40f2e937fddf");
        collectionIds.add(n1);
        int result=collectionDao.batchDeleteCollectionShopsByUser(collectionIds,"528dbe0a8d0111e6be9a40f2e937fddf");
        System.err.println("更新记录数："+result);
        //更新记录数：1 ?
	}
	
}
