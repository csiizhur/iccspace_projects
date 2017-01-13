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
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icc.baidu_data_webapi.model.hotel.JsonResult;

public class POIController {

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
	/**
	 * 矩形区域
	 * @throws Exception
	 */
	@Test
	public void PlaceBoundsTest() throws Exception{
		String arg1=URLEncoder.encode("银行", "utf-8");
		String arg3=URLEncoder.encode("39.915,116.404,39.975,116.414", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?query=%s&bounds=%s&output=json&ak=%s", 
				arg1,arg3,KEY);
		String result=getHotelByQ(url);
		System.err.println(result);
	}
	/**
	 * 城市检索区域
	 * @throws Exception
	 */
	@Test
	public void PlaceCityTest() throws Exception{
		String arg1=URLEncoder.encode("百度大厦", "utf-8");
		String arg2=URLEncoder.encode("深圳", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?query=%s&region=%s&city_limit=true&output=json&ak=%s", 
				arg1,arg2,KEY);
		String result=getHotelByQ(url);
		System.err.println(result);
	}
	/**
	 * 圆形区域检索
	 * @throws Exception
	 */
	@Test
	public void PlaceRadiusTest() throws Exception{
		String arg1=URLEncoder.encode("银行", "utf-8");
		String arg2=URLEncoder.encode("39.915,116.404", "utf-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?query=%s&location=%s&radius=%s&output=xml&ak=%s", 
				arg1,arg2,arg3,KEY);
		String result=getHotelByQ(url);
		System.err.println(result);
	}
}
