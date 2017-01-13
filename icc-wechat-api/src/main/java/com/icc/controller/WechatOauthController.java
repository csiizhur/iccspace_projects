package com.icc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.UserInfoForOauthCode;
import com.icc.common.WechatDictionary;
import com.icc.dto.UserWechatDto;
import com.icc.entity.User;
import com.icc.entity.UserSessionDto;
import com.icc.service.UserService;
import com.icc.service.WechatOauthService;
import com.icc.util.DateUtil;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpUtils;
import com.icc.wechat.exception.ParametersException;

/**
 * 
 * @description 微信授权登录 
 * @author zhur
 * @date 2016年9月14日下午3:46:47
 */
@Controller
@RequestMapping("/wechatoauth")
//@SessionAttributes(types=UserSessionDto.class)
@SessionAttributes({Constants.SESSION_USER,"userId","openId"})
public class WechatOauthController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	private static final String OauthUrl="https://open.weixin.qq.com/connect/oauth2/authorize";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WechatOauthService wechatOauthService;

	/**
	 * 授权回调地址,返回授权用户信息
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/weixinOauthRedirect")
	public  @ResponseBody String weixinOauth(
			@RequestParam(value="code",required=true) String code,
			@RequestParam(value="state",required=true) String state) throws Exception{
		logger.info("code换取access_token开始");
		UserInfoForOauthCode weixin=new UserInfoForOauthCode(code);
		Map<String,String> wmap=weixin.getUserInfo();
		
		
		return JSONObject.toJSONString(wmap);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/weixinOauth")
	public void weixinOauthAPI1() throws Exception{
		
		logger.info("进入weixinOauth");
		Map<String,String> params=new HashMap<String,String>();
		String reqUrl=OauthUrl;
		params.put("appid", GlobalConstantsPropertiesUtil.getString("appid"));
		params.put("redirect_uri", "http://139.196.228.14/icc-wechat-api/api/wechatoauth/weixinOauthRedirect");
		params.put("response_type", "code");
		params.put("scope", "snsapi_userinfo");
		params.put("state", "2#wechat_redirect");
		String codeStr=HttpUtils.sendGet(reqUrl, params);
		logger.info(codeStr);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/wexinOauth")
	public void weixinOauthAPI(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        
        /**
         * 第一步：用户同意授权，获取code:https://open.weixin.qq.com/connect/oauth2/authorize
         * ?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE
         * &state=STATE#wechat_redirect
         */
        String redirect_uri="http://42.96.144.28/WeixinApiDemo/WeixinWebServlet";// 目标访问地址
        redirect_uri=URLEncoder.encode("http://42.96.144.28/WeixinApiDemo/WeixinWebServlet","UTF-8");// 授权后重定向的回调链接地址，请使用urlencode对链接进行处理（文档要求）
        //按照文档要求拼接访问地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + GlobalConstantsPropertiesUtil.getString("appid")
                + "&redirect_uri="
                + redirect_uri
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        resp.sendRedirect(url);// 跳转到要访问的地址
	}
	
	@RequestMapping(method=RequestMethod.GET,value="")
	public @ResponseBody User getUserInfo(HttpServletRequest req) throws Exception{
		
		/**
	     * 第二步：通过code换取网页授权access_token
	     */
		String code=req.getParameter("code");
		// 同意授权
		String access_token=null;
		String refresh_token=null;
	    if (code != null) {
	        // 拼接请求地址
	        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
	                + "appid=" + GlobalConstantsPropertiesUtil.getString("appid") + "&secret="
	                + GlobalConstantsPropertiesUtil.getString("AppSecret")
	                + "&code=" + code
	                + "&grant_type=authorization_code";
	        String json = HttpUtils.sendGet(url, null);// 拿去返回值
	        //AutoWebParams autoWebParams = new AutoWebParams();
	        //Gson gson = new Gson();
	        //autoWebParams = gson.fromJson(json, new AutoWebParams().getClass());
	        access_token=JSONObject.parseObject(json).getString("access_token");
	        refresh_token=JSONObject.parseObject(json).getString("refresh_token");
	    }
	    
	    /**
	     * 第三步：刷新access_token（如果需要）
	     */
	    String url2 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
	            + "appid=" + GlobalConstantsPropertiesUtil.getString("appid")
	            + "&grant_type=refresh_token&refresh_token="
	            + refresh_token;
	    String json2 = HttpUtils.sendGet(url2, null);// 拿去返回值
	    
	    /**
	     * 第四步：拉取用户信息(需scope为 snsapi_userinfo)
	     */
	    String url3 = "https://api.weixin.qq.com/sns/userinfo?access_token="
	            + JSONObject.parseObject(json2).getString("access_token")
	            + "&openid="
	            + JSONObject.parseObject(json2).getString("openid") + "&lang=zh_CN";
	    String json3 = HttpUtils.sendGet(url3, null);// 拿去返回值
	    User userInfo = new User();
	    userInfo=(User) JSONObject.toJSON(json3);
	    
	    return userInfo;
	}
	
	/**
	 * 获取code后，请求以下链接获取access_token： 
    https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 * 获取access_token后 获取openid
	 * 存session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method=RequestMethod.GET,value="/checkOpenIdForSession2")
	public @ResponseBody JSONObject getOpenIdPutSession(String code,HttpSession httpSession,ModelMap model,int userRole) throws Exception{
		JSONObject json=new JSONObject();
		String openId=(String) model.get("openId");
		
		if(StringUtils.isEmpty(openId)){
			
			UserInfoForOauthCode weixin=null;
			Map<String,String> map=null;
			String queryOpenId=null;
			if(StringUtils.isEmpty(code)){
				
				logger.error("code is null");
				json.put("code", MessageDictionary.AUTHCODE_INVALID_CODE);
				json.put("message", MessageDictionary.AUTHCODE_INVALID_MESSAGE);
				return json;
			}else{
				weixin=new UserInfoForOauthCode(code);
				map=weixin.getUserInfo();
				queryOpenId=map.get("openid");
				String nickName=map.get("nickname");
				String sex=map.get("sex");
				String country=map.get("country");
				String province=map.get("province");
				String city=map.get("city");
				String headimgurl=map.get("headimgurl");
				User user=new User(queryOpenId,nickName,headimgurl,Integer.parseInt(sex),"",Constants.DB_VALUE_NOTDELETE,DateUtil.dateToTimestamp(new Date()));

				//存储用户信息
				//身份设置
				User userdao=userService.insertUserAndUpdateUserRole(user, userRole);
				if(userdao!=null){
					
					json.put("code", MessageDictionary.OPENID_AVAILABLE_CODE);
					json.put("message", MessageDictionary.AUTHCODE_INVALID_MESSAGE);
					model.addAttribute("openId",queryOpenId);
					model.addAttribute("userId", userdao.getUserId());
					UserSessionDto session_user=new UserSessionDto();
					session_user.setOpenId(queryOpenId);
					session_user.setUserId(userdao.getUserId());
					model.addAttribute(Constants.SESSION_USER, session_user);
					json.put("openId", queryOpenId);
					return json;
					
				}else{
					json.put("code", MessageDictionary.RESP_ERROR_CODE);
					json.put("message", MessageDictionary.RESP_ERROR_MSG);
					return json;
				}
			}
		}else{
			
			json.put("code", MessageDictionary.OPENID_IN_SESSION_CODE);
			json.put("message", MessageDictionary.OPENID_IN_SESSION_MESSAGE);
			json.put("openId", openId);
			return json;
		}
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/checkOpenIdForSession")
	public @ResponseBody JSONObject getOpenIdForCodePutSession(String code) throws Exception{
		JSONObject json=new JSONObject();
		
		UserInfoForOauthCode weixin=null;
		Map<String,String> map=null;
		String queryOpenId=null;
		if(StringUtils.isEmpty(code)){
			logger.error("code is null");
			json.put("code", MessageDictionary.AUTHCODE_INVALID_CODE);
			json.put("message", MessageDictionary.AUTHCODE_INVALID_MESSAGE);
		}else{
			
			weixin=new UserInfoForOauthCode(code);
			map=weixin.getUserInfo();
			logger.info("返回CODE——>access_token--->换取的用户信息："+JSONObject.toJSONString(map));
			//{country=null, province=null, city=null, openid=null, sex=null, nickname=null, headimgurl=null}
			if (map!=null&&map.get("openid")!=null) {
				queryOpenId = map.get("openid");
				String nickName = map.get("nickname");
				String sex = map.get("sex");
				String country = map.get("country");
				String province = map.get("province");
				String city = map.get("city");
				String headimgurl = map.get("headimgurl");
				User user = new User(queryOpenId, nickName, headimgurl, Integer.parseInt(sex), "",
						Constants.DB_VALUE_NOTDELETE, DateUtil.dateToTimestamp(new Date()));
				//获取用户信息
				User userdao = userService.getUserInfoByOpenId(queryOpenId);
				if (userdao != null) {

					json.put("code", MessageDictionary.OPENID_AVAILABLE_CODE);
					json.put("message", MessageDictionary.AUTHCODE_INVALID_MESSAGE);
					UserSessionDto session_user = new UserSessionDto();
					session_user.setOpenId(queryOpenId);
					session_user.setUserId(userdao.getUserId());
					json.put("userId", userdao.getUserId());
				} else {
					json.put("code", MessageDictionary.RESP_ERROR_CODE);
					json.put("message", "openId获取userId失败");
				} 
			}else{
				json.put("code", "0099");
				json.put("message", "获取用户信息失败");
			}
		}
		return json;
	}
	/**
	 * 检查session中的openId
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getOpenId")
	public @ResponseBody UserWechatDto getOpenId(ModelMap model){
		UserWechatDto userWechatDto=new UserWechatDto();
		
		if (model.size() == Constants.SIZE_0) {
			throw new ParametersException(WechatDictionary.ERROR_CODE_N1, WechatDictionary.ERROR_CODE_N1_DESC);
		} else {
			userWechatDto=wechatOauthService.getUserInfoByModelMap(model, userWechatDto);

			return userWechatDto;
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/testSession")
	public @ResponseBody JSON getOpenIdPutSession1(String code,ModelMap model) throws Exception{
		String userId="528dbe0a8d0111e6be9a40f2e937fddf";
		model.addAttribute("openId", "oBfPWv7Q23DXTkIee_H6f_X6vNQY");
		model.addAttribute("userId", userId);
		UserSessionDto user=new UserSessionDto();
		user.setOpenId("oBfPWv7Q23DXTkIee_H6f_X6vNQY");
		user.setUserId("528dbe0a8d0111e6be9a40f2e937fddf");
		model.addAttribute(Constants.SESSION_USER, user);
		User u=userService.getUserInfoByUserId(userId);
		user.setUserRole(u.getUserRole());
		return (JSON) JSONObject.toJSON(user);
	}
	
}
