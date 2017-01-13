package com.icc.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description 日期类型工具类 
 * @author zhur
 * @date 2016年9月14日下午2:08:00
 */
public class DateUtil {

	private static Logger logger=LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * date类型转String
	 * @param date
	 * @param type
	 * @return
	 */
	public static String dateToString(Date date,String type){
		
		String str=null;
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		if(("SHORT").equals(type)){
			format=DateFormat.getDateInstance(DateFormat.SHORT);
			str=format.format(date);
			//16-9-14
		}else if(("MEDIUM").equals(type)){
			format=DateFormat.getDateInstance(DateFormat.MEDIUM);
			str=format.format(date);
			//2016-9-14
		}else if(("FULL").equals(type)){
			format=DateFormat.getDateInstance(DateFormat.FULL);
			str=format.format(date);
			//2016年9月14日 星期三
		}
		
		return str;
	}
	/**
	 * date转timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date){
		Timestamp ts=null;
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=format.format(date);
		
		ts=Timestamp.valueOf(str);
		return ts;
		//2016-09-14 14:37:06.0
	}
	/**
	 * String转Date
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		
		try {
			date=format.parse(str);
		} catch (ParseException e) {
			logger.error("字符转String失败===》");
			e.printStackTrace();
		}
		return date;
		//Wed Sep 14 00:00:00 CST 2016
	}
	/**
	 * String转Date
	 * @param str
	 * @return
	 */
	public static Date stringToDate2(String str){
		
		DateFormat format=new SimpleDateFormat("yyyy年MM月");
		Date date=null;
		
		try {
			date=format.parse(str);
		} catch (ParseException e) {
			logger.error("字符转String失败===》");
			e.printStackTrace();
		}
		return date;
		//Wed Sep 14 00:00:00 CST 2016
	}
	/**
	 * String转SqlDate类型
	 * @param str
	 * @return
	 */
	public static Date stringToSqlDate(String str){
		return java.sql.Date.valueOf(str);
		//2019-09-09
	}
	/**
	 * String转Timestamp
	 * @param str
	 * @return
	 */
	public static Timestamp stringToTimestamp(String str){
		return Timestamp.valueOf(str);
	}
	/**
	 * Timestamp转String
	 * @param ts
	 * @return
	 */
	public static String timestampToString(Timestamp ts){
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//2016-09-14 14:48:36
		return format.format(ts);
	}
	public static void main(String[] args) {
		//logger.error(timestampToString(new Timestamp(System.currentTimeMillis())));
		//logger.error(stringToTimestamp("2016-09-06 15:15:43")+"===");
		//String str=stringToDate2("2016年1月")+"";
		//logger.error(str);
		//String str2=DateUtil.stringToTimestamp(str)+"";
		//logger.error(str2);
		logger.error(dateToTimestamp(stringToDate2("2016年1月"))+"");
	}
}
