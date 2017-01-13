package com.icc.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @description springmvc接收数据 日期转换
 * @author zhur
 * @date 2016年8月30日下午4:13:21
 */
public class DateConverter implements Converter<Date, String> {

	@Override
	public String convert(Date source) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		return sdf.format(source);
	}


}