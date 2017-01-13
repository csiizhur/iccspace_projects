package com.icc.common;

/**
 * 
 * @description hashcode工厂
 * @author zhurun
 * @date 2016年10月9日上午9:48:47
 */
public class HashCodeFactory {

	public final static String a="a";
	public int getHashCode(String... args){
		
		StringBuilder sb=new StringBuilder();
		for(String tm:args){
			sb.append(tm);
		}
		return sb.hashCode();
	}
	
	public StringBuffer getStrUnique(String... args){
		StringBuffer sb=new StringBuffer();
		
		for(String tmp:args){
			sb.append(tmp);
		}
		return sb;
	}
	public static void main(String[] args) {
		System.err.println(new HashCodeFactory().getHashCode(a));
		System.err.println(new HashCodeFactory().getHashCode(a));
		System.err.println(new HashCodeFactory().getStrUnique(a,a,a));
		System.err.println(new HashCodeFactory().getStrUnique(a,a,a));
	}
}
