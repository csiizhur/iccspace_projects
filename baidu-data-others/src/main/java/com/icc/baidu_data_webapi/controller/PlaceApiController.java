package com.icc.baidu_data_webapi.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icc.baidu_data_webapi.model.hotel.JsonResult;
import com.icc.baidu_data_webapi.model.hotel.Location;

@Controller
public class PlaceApiController {

	private static final String KEY="O6RW0LGNNync3xcSU4N8bG3ctdfax9q5";
	
	public static String getHotelByQ(String url){
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			httpClient.executeMethod(getMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = getMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			responseMsg = out.toString("UTF-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getPoiByLocation")
	public @ResponseBody JsonResult getPoiByLocation(int pageSize,int pageNum,Location l,String q) throws JsonParseException, JsonMappingException, IOException{
		String arg=URLEncoder.encode(q, "UTF-8");
		String arg2=URLEncoder.encode(l.getLat()+","+l.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s", arg,arg2,arg3,arg4,KEY);
		String result=getHotelByQ(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result, JsonResult.class);
		System.err.println(r.getResults());
		
		return r;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String args1=URLEncoder.encode("酒店$ATM$小区$银行", "UTF-8");
		String args2=URLEncoder.encode("31.35568111018402,120.68639710491348", "UTF-8");
		String args3=URLEncoder.encode("2000", "UTF-8");
		String args4=URLEncoder.encode("sort_name:distance|sort_rule:1", "UTF-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s", 
				args1,args2,args3,args4,KEY);
		String result=getHotelByQ(url);
		System.err.println(result);
		
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			JsonResult r=objectMapper.readValue(result, JsonResult.class);
			System.err.println(r.getResults());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
