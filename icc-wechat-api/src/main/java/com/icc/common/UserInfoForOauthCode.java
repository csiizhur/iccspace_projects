package com.icc.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpUtils;

/**
 *  //1,code换取access_token
	//2,刷新access_token(如果需要)
	//3,拉取用户信息
 * @description 用户oauth2.0授权登录 通过code获取用户真实信息
 * @author zhur
 * @date 2016年9月14日下午5:43:24
 */
public class UserInfoForOauthCode {

	private Logger log=LoggerFactory.getLogger(getClass());
	private String openId;
	private String access_token;
	private String code;
	private String unionid;
	private HashMap<String ,String> params=new HashMap<String,String>();
	
	public UserInfoForOauthCode() {
	}

	public UserInfoForOauthCode(String code) {
		this.code = code;
		/*params.put("appid", "appid");
		params.put("secret", "AppSecret");*/
		params.put("appid", GlobalConstantsPropertiesUtil.getString("appid"));
		params.put("secret", GlobalConstantsPropertiesUtil.getString("AppSecret"));
	}
	
	/*
	 * 1,将用户信息拼装成map,
	 * 2,通过code获取access_token,openid,unionid
	 * 3,通过openid获取详细信息
	 * 4,判断是否关注
	 * 5,再返回map
	 */
	public HashMap<String,String> getUserInfo() throws Exception{
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		
		String tokenStr=HttpUtils.sendGet(GlobalConstantsPropertiesUtil.getString("OauthCodeUrl"), params);
		log.info("CODE换取的access_token openId:"+tokenStr);
		//当返回到授权页面 access_token报错
		//{"errcode":40029,"errmsg":"invalid code, hints: [ req_id: 5Fdj.a0749ns53 ]"}
		//access_token过期了
		access_token=JSONObject.parseObject(tokenStr).getString("access_token");
		
		
		openId=JSONObject.parseObject(tokenStr).getString("openid");
		//refresh_token有效期30天
		String refresh_token=JSONObject.parseObject(tokenStr).getString("refresh_token");
		//unionid=JSONObject.fromObject(tokenStr).getString("unionid");
		
		params.clear();
		params.put("access_token", access_token);
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		log.info("access_token:"+access_token+"openId:"+openId);
		String userInfos=HttpUtils.sendGet(GlobalConstantsPropertiesUtil.getString("OauthInfoUrl"), params);
		log.info("access_token openId获取用户详细信息："+JSONObject.toJSONString(userInfos));
		
		params.clear();
		params.put("access_token", GlobalConstantsPropertiesUtil.getString("access_token"));
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		String subscribers="";
		//subscribers=HttpUtils.sendGet(GlobalConstants.getInterfaceUrl("SubscribeUrl"), params);
		
		params.clear();
		//params.put("subscribe",JSONObject.fromObject(subscribers).getString("subscribr"));
		params.put("openid", openId);
		//params.put("unionid", unionid);
		params.put("nickname", JSONObject.parseObject(userInfos).getString("nickname"));
		params.put("sex", JSONObject.parseObject(userInfos).getString("sex"));
		params.put("country", JSONObject.parseObject(userInfos).getString("country"));
		params.put("province", JSONObject.parseObject(userInfos).getString("province"));
		params.put("city", JSONObject.parseObject(userInfos).getString("city"));
		params.put("headimgurl", JSONObject.parseObject(userInfos).getString("headimgurl"));
		
		return params;
	}
	
	/***
	 * 
	 * @param openid
	 * @return
	 * @throws Exception 
	 */
	public static HashMap<String,String> Openid_userInfo(String openid) throws Exception{
		HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token",
                GlobalConstantsPropertiesUtil.getString("access_token"));
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(GlobalConstantsPropertiesUtil.getString("SubscribeUrl"), params);
        params.clear();
        params.put("nickname",
                JSONObject.parseObject(subscribers).getString("nickname"));
        params.put("headimgurl",
                JSONObject.parseObject(subscribers).getString("headimgurl"));
        params.put("sex", JSONObject.parseObject(subscribers).getString("sex"));
        return params;
	}
	
	@SuppressWarnings("unused")
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
