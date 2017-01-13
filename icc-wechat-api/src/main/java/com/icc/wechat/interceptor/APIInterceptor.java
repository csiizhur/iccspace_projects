package com.icc.wechat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.icc.common.Constants;
import com.icc.entity.UserSessionDto;

/**
 * 
 * @description api 请求中加入token验证
 * 客户端加密
 * 服务端解密
 * 验证加密规则
 * @author 朱润
 * @date 2016年9月22日上午10:12:56
 */
public class APIInterceptor extends HandlerInterceptorAdapter {

	private final static Logger logger=LoggerFactory.getLogger(APIInterceptor.class);
	
	private final static String testSessionUrl="/testSession";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String token=request.getParameter("token");
		String user_token=response.getHeader("user_token");
		String session_token=null;
		UserSessionDto user=(UserSessionDto)request.getSession().getAttribute(Constants.SESSION_USER);
		if(user!=null){
			session_token=user.getOpenId(); 
		}else{
			
			session_token=(String) request.getSession().getAttribute("openId");
		}
		
		request.getContextPath();
		
		request.getRequestURL();
		
		request.getRequestURI();
		
		request.getServletPath();
		
		request.getQueryString();
		
		//获取当前请求url
		StringBuffer requestUrl=request.getRequestURL();
		
		if(testSessionUrl.equals(requestUrl.substring(requestUrl.lastIndexOf("/"), requestUrl.length()))){
			return true;
		}
		if(user_token==null){
			return false;//暂时返回false
		}else{
			logger.info(request.getRemoteAddr());
			logger.info("response_headers_token===>"+user_token);
			logger.info("session_token===>"+session_token);
			return true;		
		}
		
		/*Enumeration<String> paraKeys=request.getParameterNames();
		String encodeStr="";
		while(paraKeys.hasMoreElements()){
			String paraKey=paraKeys.nextElement();
			if(paraKey.equals("token")){
				break;
			}
			String paraValue=request.getParameter(paraKey);
			encodeStr+=paraValue;
		}
		if(!token.equals(DigestUtils.md5(encodeStr))){
			response.setStatus(500);
			return false;
		}*/
		
	}
	

	public static void main(String[] args) {
		System.err.println(DigestUtils.md5("zhur"));
		System.err.println(DigestUtils.md5Hex("[B@edcd21"));
	}
	
}
