package com.icc.common;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.wechat.exception.ParametersException;

/**
 * 
 * @Description 用户微信前端页面的jssdk配置使用
 * @ClassName JSSDKConfig.java
 * @author zhur
 * @date 2016年11月22日下午4:31:53
 */
public class JSSDKConfig {

	private static Logger logger=LoggerFactory.getLogger(JSSDKConfig.class);
	/**
	 * 前端jssdk页面配置需要用到的配置参数
	 * @param url
	 * @return hashmap {appid,timestamp,nonceStr,signature}
	 * @throws Exception
	 * @author Administrator-zhur
	 * @date 2016年6月28日 下午5:00:19
	 */
	public static HashMap<String, String> jsSDK_Sign(String url) throws Exception {
		if(StringUtils.isNotEmpty(url)){
			url=URLDecoder.decode(url);
		}else{
			throw new ParametersException("url is null");
		}
		String nonce_str = create_nonce_str();
		String timestamp=create_timestamp();
		String jsapi_ticket=GlobalConstantsPropertiesUtil.getString("jsapi_ticket");
		// 注意这里参数名必须全部小写，且必须有序
		String  string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str+ "&timestamp=" + timestamp  + "&url=" + url;
		logger.info("string1:"+string1);
		String signature=SHA1(string1);
		//String signature1=getSHA1(jsapi_ticket, nonce_str, timestamp, url);
		//String signature2=stringToByteToHex(string1);
		logger.info("signature:"+signature);
		//logger.info("signature1:"+signature1);
		//logger.info("signature2:"+signature2);
		
		HashMap<String, String> jssdk=new HashMap<String, String>();
		jssdk.put("appId", GlobalConstantsPropertiesUtil.getString("appid"));
		jssdk.put("timestamp", timestamp);
		jssdk.put("nonceStr", nonce_str);
		jssdk.put("signature", signature);
		return jssdk;

	}
	/**
	 * 对拼接的字符串进行SHA1签名得到signature
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript) {  
	    try {  
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");  
	        digest.update(decript.getBytes());  
	        byte messageDigest[] = digest.digest();  
	        // Create Hex String  
	        StringBuffer hexString = new StringBuffer();  
	        // 字节数组转换为 十六进制 数  
	            for (int i = 0; i < messageDigest.length; i++) {  
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
	                if (shaHex.length() < 2) {  
	                    hexString.append(0);  
	                }  
	                hexString.append(shaHex);  
	            }  
	            return hexString.toString();  
	   
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	}
	
	public static void main(String[] args) {
		System.err.println(SHA1("jsapi_ticket=kgt8ON7yVITDhtdwci0qeTjgi3nn-jKT7try1GQBBtoiA0O7AtuhPijBXSWvNi20YJlmu_i3y8JD4zUFgdT-SQ&noncestr=49c0f1f3-b39b-4032-aa55-d775eedc9159&timestamp=1480659236&url=http://www.iccspace.cn/tenant/fabu/qiuzufabu.html"));
	}
	/**
	 * 数组排序后转字符串签名
	 * @param jsapi_ticket
	 * @param nonce
	 * @param timestamp
	 * @param jsurl
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSHA1(String jsapi_ticket, String nonce,String timestamp,String jsurl)
            throws NoSuchAlgorithmException {
		
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,"timestamp=" + timestamp, "noncestr=" + nonce, "url=" + jsurl };
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2]).concat("&"+paramArr[3]);
		System.out.println("拼接之后的content为:"+content);        
        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(content.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        return hexstr.toString();
    }
	/**
	 * Groovy签名
	 * @param hash
	 * @return
	 */
	private static String stringToByteToHex(String string1) {
		MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			
			crypt.update(string1.getBytes("UTF-8"));
			
			byte[] hash=crypt.digest();
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String signature = byteToHex(crypt.digest());
		return null;
	}
	
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}
	
	private static String create_timestamp(){
		return Long.toString(Calendar.getInstance().getTimeInMillis()/1000);
	}

}