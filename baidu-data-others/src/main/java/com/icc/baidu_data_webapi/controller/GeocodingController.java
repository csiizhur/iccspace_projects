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

public class GeocodingController {

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
			//释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String args1=URLEncoder.encode("北京市海淀区上地十街10号", "UTF-8");
		String args2=URLEncoder.encode("苏州工业园区金鸡湖大道1355号国际科技园三期科技广场", "UTF-8");
		String args3=URLEncoder.encode("苏州吴中区和顺路1号", "UTF-8");//120.68639710491348,"lat":31.35568111018402
		String args4=URLEncoder.encode("苏州园区苏州吴中区和顺路一号415室", "UTF-8");//120.68639710491348,"lat":31.35568111018402
		String url3=String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s", 
				args3,KEY);
		String url4=String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s", 
				args4,KEY);
		String result3=getHotelByQ(url3);
		String result4=getHotelByQ(url4);
		System.err.println(result3);
		System.err.println(result4);
		
	}
	@Test
	public void testG() throws Exception{
		String args1=URLEncoder.encode("120.525726318359380,31.261020660400390", "UTF-8");
		String url3=String.format("http://api.map.baidu.com/geocoder/v2/?location=%s&output=json&ak=%s&callback=renderReverse", 
				args1,KEY);
		String result3=getHotelByQ(url3);
		System.err.println(result3);
	}
}
