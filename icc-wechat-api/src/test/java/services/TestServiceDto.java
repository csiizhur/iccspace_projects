package services;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

import com.icc.common.Constants;
import com.icc.dao.EntityAccessLogDao;
import com.icc.dao.UserDao;
import com.icc.dto.UserWechatDto;
import com.icc.entity.EntityAccessLog;
import com.icc.entity.User;
import com.icc.service.ShopsService;
import com.icc.service.TestTransactionService;
import com.icc.service.UserService;
import com.icc.service.WechatOauthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class TestServiceDto {

	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private WechatOauthService wechatOauthService;
	@Autowired
	private TestTransactionService testTransactionService;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopsService shopService;
	@Autowired
	private EntityAccessLogDao accessLogDao;
	@Autowired
	private UserDao userDao;
	@Test
	public void testSession(){
		ModelMap map=new ModelMap();
		UserWechatDto userWechatDto=new UserWechatDto();
		map.put("openId", "oBfPWv7Q23DXTkIee_H6f_X6vNQY");
		userWechatDto=wechatOauthService.getUserInfoByModelMap(map,userWechatDto);
		logger.info(userWechatDto.toString());
	}
	
	@Test
	public void testTransaction(){
		testTransactionService.testTransaction();
	}
	
	@Test
	public void testAOP(){
		//userService.queryUUID();
		Map<String,Object> map=shopService.queryShopsInfoById("d68c1cabeb0d408cb76fae4fe586f43b","");//queryShopsInfoById
		//logger.error(map.toString());
		//shopService.queryShopsAndUpdateClick("d68c1cabeb0d408cb76fae4fe586f43b");
		//logger.info(shopService.getHotSearchShopsList(0).size()+"===========");
	}
	
	@Test
	public void testEntityAccessLog(){
		String uu=userDao.queryUUID();
		logger.info(uu+"=========----------==========");
		EntityAccessLog ea = new EntityAccessLog();
		ea.setCreateBy(Constants.DEFAULT_CREATER_UPDATER);
		ea.setMethodName("w");
		ea.setOperationTime(new Date());
		ea.setOperator("");
		ea.setParamsContent("d");
		ea.setServiceName("df");
		accessLogDao.create(ea);
		User list=accessLogDao.queryUser();
		logger.info(list+"====");
	}
}
