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

public class PlaceSuggestionController {

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
		String args1=URLEncoder.encode("天安门", "UTF-8");
		String args2=URLEncoder.encode("131", "UTF-8");
		String args3=URLEncoder.encode("E4805d16520de693a3fe707cdc962045", "UTF-8");
		String url=String.format("http://api.map.baidu.com/place/v2/suggestion?query=%s&region=%s&output=json&ak=%s", 
				args1,args2,KEY);
		String result=getHotelByQ(url);
		System.err.println(result);
		
	}
}
