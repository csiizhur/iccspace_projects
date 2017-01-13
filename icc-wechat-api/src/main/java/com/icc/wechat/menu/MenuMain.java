package com.icc.wechat.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icc.util.HttpUtils;

/**
 * 
 * @description 自定义菜单主类
 * @author zhurun
 * @date 2016年9月19日下午1:51:25
 */
public class MenuMain {
	
	private static final String access_token="wPuQmsnqhQ3TbRuKE3Ghn6NjkyBltbbEzqSspz2fDVaYRG24Rea8K3n6uh5lXM3sbw3ZAvi-YvUDLNbNR-Oo7YIxXs4bG-0UGN6flqLmN_40kvBS2itFuo2sxFHIPVTEGVHiAFAAPB";
	
	private static final String menu_create_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	private static final String menu_delte_url = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	private static final String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get";
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void createMenu(){
		MenuButton mb1=new MenuButton();
		mb1.setName("出租");
		mb1.setType("view");
		mb1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2560032a17c12e64&redirect_uri=http%3a%2f%2fwww.iccspace.cn%2fproperty%2fzuhu%2fzuhulist.html&response_type=code&scope=snsapi_userinfo&state=wechat#wechat_redirect");
		MenuButton mb2=new MenuButton();
		mb2.setName("求租");
		mb2.setType("view");
		mb2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2560032a17c12e64&redirect_uri=http%3a%2f%2fwww.iccspace.cn%2ftenant%2fshop%2fshoplist.html&response_type=code&scope=snsapi_userinfo&state=wechat#wechat_redirect");
		MenuButton mb3=new MenuButton();
		mb3.setName("推荐");
		mb3.setType("view");
		mb3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2560032a17c12e64&redirect_uri=http%3a%2f%2fwww.iccspace.cn%2fserve%2fproperty.html&response_type=code&scope=snsapi_base&state=wechat#wechat_redirect");
		MenuButton mb4=new MenuButton();
		mb4.setName("合作");
		mb4.setType("view");
		mb4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2560032a17c12e64&redirect_uri=http%3a%2f%2fwww.iccspace.cn%2fserve%2fcollaborate.html&response_type=code&scope=snsapi_base&state=wechat#wechat_redirect");
		MenuButton mb5=new MenuButton();
		mb5.setName("留言");
		mb5.setType("view");
		mb5.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2560032a17c12e64&redirect_uri=http%3a%2f%2fwww.iccspace.cn%2fserve%2fmessage.html&response_type=code&scope=snsapi_base&state=wechat#wechat_redirect");
		
		JSONArray sub_button=new JSONArray();
		sub_button.add(mb3);
		sub_button.add(mb4);
		sub_button.add(mb5);
		JSONObject buttonOne=new JSONObject();
		buttonOne.put("name", "服务");
		buttonOne.put("sub_button", sub_button);
		
		JSONArray button=new JSONArray();
		button.add(mb1);
		button.add(mb2);
		button.add(buttonOne);
		
		JSONObject menu=new JSONObject();
		menu.put("button", button);
		System.err.println(JSONObject.toJSON(button));
		
		String jsonData=JSONObject.toJSON(menu).toString();
		
		logger.info(jsonData);
		String result=null;
		
		try {
			result=HttpUtils.sendPostBuffer(menu_create_url+access_token, jsonData);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(result);
	}
	
	@Test
	public void deleteMenu(){
		TreeMap<String,String> params=new TreeMap<String,String>();
		params.put("access_token", access_token);
		String result=null;
		try {
			result=HttpUtils.sendPost(menu_delte_url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=JSONObject.parseObject(result);
		if(json.getInteger("errcode") !=0 || json.getString("errmsg") !="ok"){
			logger.error(json.getString("errmsg"));
		}
	}
	
	@Test
	public void queryMenu(){
		Map<String,String> params=new HashMap<String,String>();
		params.put("access_token", access_token);
		try {
			String result=HttpUtils.sendGet(menu_get_url, params);
			logger.info(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
