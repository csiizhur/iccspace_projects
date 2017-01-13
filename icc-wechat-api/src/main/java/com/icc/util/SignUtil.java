package com.icc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description 请求校验
 * @author zhur
 * @date 2016年9月9日上午10:02:06
 */
public class SignUtil {

	private static Logger log=LoggerFactory.getLogger(SignUtil.class);
	
	private static String token="wechat_iccspace";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		log.info("==========开始校验=======");
		
		String[] arr=new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sb.append(arr[i]);
		}
		
		MessageDigest md=null;
		String tmpStr=null;
		
		try{
			md=MessageDigest.getInstance("SHA-1");
			byte[] digest=md.digest(sb.toString().getBytes());
			tmpStr=byteToStr(digest);
			
		}catch (NoSuchAlgorithmException  e) {
			log.error("===checkSignature  error");
		}
		
		sb=null;
		log.error(tmpStr);
		log.error(signature);
		return tmpStr!=null ?tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 字节数组转换成十六进制字符串
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		String strDigest="";
		for(int i=0;i<digest.length;i++){
			strDigest+=byteToHexStr(digest[i]);
		}
		return strDigest;
	}

	/**
	 * 字节转换成十六进制字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		char[] Digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		char[] tmpArr=new char[2];
		
		tmpArr[0]=Digit[(b>>>4) & 0X0F];
		
		tmpArr[1]=Digit[b & 0X0F];
		
		return new String(tmpArr);
	}
}
