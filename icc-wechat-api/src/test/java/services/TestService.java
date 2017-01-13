package services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.common.IdFactory;
import com.icc.dao.PropertysDao;
import com.icc.dao.ShopsDao;
import com.icc.dao.TextMessageDao;
import com.icc.dao.UserDao;
import com.icc.dto.UserExpectShopsDto;
import com.icc.entity.User;
import com.icc.service.UserService;
import com.icc.service.impl.UserServiceImpl;
import com.icc.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class TestService {

	private final Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TextMessageDao textMessageDao;
	@Autowired
	private ShopsDao shopsDao;
	@Autowired
	private PropertysDao propertysDao;
	@Autowired
	private TextMessageDao messageDao;
	@Autowired UserDao userDao;
	@Autowired IdFactory idFactory;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testMessage(){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("keyWord", "好");
		m.put("deleted", 0);
		String str=textMessageDao.queryKeyWordForReply(m);
		System.err.println(str);
	}
	@Test
	public void testUserReleaseShops(){
		List<Map<String,Object>> list=shopsDao.queryShopInformationByUserId("1239c6c8757011e6be9a40f2e937fddf");
		System.err.println(list);
	}
	@Test
	public void testUserReleaseShopsDetail(){
		Map<String,Object> list=shopsDao.queryShopsInfoById("fdc81e29798a11e684e200163e008fcd");
		System.err.println(list);
	}
	@Test
	public void testPropertysShopsDetail(){
		Map<String,Object> list=propertysDao.queryPropertyInfoForShopsId("fdc81e29798a11e684e200163e008fcd");
		System.err.println(list);
	}
	@Test
	public void insertUsersInfo(){
		User u=new User();
		u.setAge(11);
		u.setSubscribeTime(DateUtil.dateToTimestamp(new Date()));
		u.setLastAccessTime(DateUtil.dateToTimestamp(new Date()));
		userDao.insertUsersInfo(u);
	}
	
	@Test
	public void testUUID(){
		log.info(idFactory.getUUID()+"=======");
	}
	
	@Test
	public void testSort(){
		UserExpectShopsDto dto=new UserExpectShopsDto();
		//m12.put("address", "观前街");
		//m12.put("shopSize", 50);		
		//m12.put("businessType", "住宅");
		//m12.put("startShopSize", 50*0.5);
		//m12.put("endShopSize", 50*1.5);
		dto.setRentFee(BigDecimal.valueOf(100L));
		List<Map<Object,Object>> list=shopsDao.queryRecommendShopsBySort(dto);
		
		for(Map m:list){
			log.error(m.get("SHOP_SIZE").toString()+"=="+m.get("shopSorts"));
		}
		log.error(list.size()+"");
		log.debug(list.toString());
 	}
	@Test
	public void testUserInfo(){
		User user=new User("","oBfPWv7Q23DXTkIee_H6f_X6vNQY");
		User u=userDao.getUserInfo(user);
		log.info(u.toString());
	}
	
	@Test
	public void testUser(){
		//userService.insertUserAndUpdateUserRole(new User("","dcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdcdc"), com.icc.common.Constants.USER_ROLE_LEASE);
		
		userService.getUserInfoByOpenId("oBfPWv7Q23DXTkIee_H6f_X6vNQY");
	}
}
