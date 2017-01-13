package com.zhur.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.icc.aliyun.opensearch.OpensearchTask;
import com.icc.dao.ShopsHistoryDao;
import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.service.UserService;
import com.icc.util.HttpUtils;
import com.icc.wechat.common.WechatConstants;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:/spring/applicationContext.xml","classpath:/spring/spring-dao.xml"})
@ContextConfiguration(locations={"classpath:/spring/applicationContext.xml"})
public class CacheUserTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template;
	@Autowired
	private UserService userService;
	@Autowired
	private ShopsHistoryDao historyDao;
	/**
	 * mybatis缓存--dao
	 * @throws Exception 
	 */
	@Test
	public void testDaoCache() throws Exception{
		/*Reader reader = Resources.getResourceAsReader("spring/applicationContext.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userMapper = sqlSession.getMapper(UserDao.class);*/
		
		SqlSession sqlSession=sqlSessionFactory.openSession();
		UserDao userMapper=sqlSession.getMapper(UserDao.class);
		userMapper.getUserInfoByOpenId("oBfPWvy-mssnfIBJgMUeUJO4u1yE");
		userMapper.getUserInfoByOpenId("oBfPWvy-mssnfIBJgMUeUJO4u1yE");
	}
	@Test
	public void testDaoCache2() throws Exception{
		/*Reader reader = Resources.getResourceAsReader("spring/applicationContext.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDao userMapper = sqlSession.getMapper(UserDao.class);*/
		//<cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true" />
		//<setting name="cacheEnabled" value="true" />默认已经开启
		userDao.getUserInfoByOpenId("oBfPWvy-mssnfIBJgMUeUJO4u1yE");
		userDao.getUserInfoByOpenId("oBfPWvy-mssnfIBJgMUeUJO4u1yE");
	}
	
	/**
	 * mybatis缓存--service
	 */
	@Test
	public void testServiceCache(){
		//userService.queryUserIsExists("oBfPWv7Q23DXTkIee_H6f_X6vNQY");
		//userService.queryUserIsExists("oBfPWv7Q23DXTkIee_H6f_X6vNQY");
	}
	
	@Test
	public void testIocBean(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
		OpensearchTask ot=(OpensearchTask) ac.getBean("opensearchTask");
	}
	@Test
	public void testRedisCache() throws Exception{
		userService.selectByPrimaryKey("9713d282c35c11e6b0d000163e020977");
		
	}
	@Test
	public void testRedisCache2() throws Exception{
		User user=new User();
		user.setNickName("luu");
		userService.insertSelective(user);
		
	}
	@Test
	public void testRedisCache3() throws Exception{
		userService.deleteByPrimaryKey("f627ba5cc35b11e6b0d000163e020977");
		
	}
	@Test
	public void testRedisCache4() throws Exception{
		selectByPrimaryKey("f627ba5cc35b11e6b0d000163e020977");
		
	}
	@Cacheable(value="common",key="'id_'+#id")
	public User selectByPrimaryKey(String id) {
		return userDao.getUserInfoByUserId(id);
	}
	@Cacheable("access_token")
	public Map<String,String> getTokenAndTicket() throws Exception{
		Map<String,String> params=new HashMap<String,String>();
		
		params.put("grant_type", "client_credential");
		params.put("appid", "wx2560032a17c12e64");
		params.put("secret", "1e7b61c36e559d4e25c20102a8bf0493");
		
		String token=HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
		String access_token=JSONObject.parseObject(token).getString(WechatConstants.access_token);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("access_token", access_token);
		
		params.clear();
        params.put(WechatConstants.access_token, access_token);
        params.put("type", WechatConstants.jsapi_ticket_type);
        String jsticket = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", params);
        String jsapi_ticket = JSONObject.parseObject(jsticket).getString(WechatConstants.jsapi_ticket_name);
        map.put("jsapi_ticket", jsapi_ticket);
		return map;
	}
	@Test
	public void opensearchData(){
		List<Map<String,Object>> l=historyDao.queryShopsHistoryForUpdateOpensearch();
		List<Map<String,Object>> json=new ArrayList<Map<String,Object>>();
		for(Map<String,Object> o:l){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("fields", o);
			m.put("cmd", "ADD");
			json.add(m);
		}
		System.out.println(JSONArray.fromObject(json));
	}
}
