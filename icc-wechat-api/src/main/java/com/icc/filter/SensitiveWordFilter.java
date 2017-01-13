package com.icc.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.icc.util.SensitiveWordUtil;
/**
 * 只对评论的敏感词过滤
 * @description
 * @author zhurun
 * @date 2016年11月18日下午2:56:24
 */
public class SensitiveWordFilter implements Filter{
	private FilterConfig filterConfig;
	
	public static HashMap keyMap = null;
	public static String path;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		String keyWordPath = filterConfig.getInitParameter("key");
		path = filterConfig.getServletContext().getRealPath(keyWordPath);
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if(keyMap == null){
				keyMap = (HashMap)SensitiveWordUtil.readProperties(path);
		}
		if(req.getMethod().equals("POST")){
			chain.doFilter(new SensitiveWordRequestWrapper(req,keyMap), response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null; 
	}

	@Test
	public void loadProperties(){
		//path="F:\\company\\Administrator\\iccspace\\icc-wechat-api\\src\\main\\resources\\config\\sensitiveword.properties";
		path="classpath:/config/sensitiveword.properties";
		keyMap = (HashMap)SensitiveWordUtil.readProperties(path);
	}
}
