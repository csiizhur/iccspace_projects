package com.icc.baidu_data_webapi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpConnectUtil {
	
	private static String AK="O6RW0LGNNync3xcSU4N8bG3ctdfax9q5";
	
	public static String getHttp(String url){
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

	/**
	 * post方式
	 * @param url
	 * @param code
	 * @param type
	 * @author www.yoodb.com
	 * @return
	 */
	public static String postHttp(String url,String code,String type) {
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("GBK");
		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter(type, code);
		postMethod.addParameter("client_secret", AK);
		try {
			httpClient.executeMethod(postMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = postMethod.getResponseBodyAsStream();
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
			postMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	public static void main(String[] args) {
		getHttp("http://api.map.baidu.com/place/v2/search?q=饭店&region=北京&output=json&ak=O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
	}
}

