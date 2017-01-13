package com.icc.ascii;

import org.junit.Test;

public class AsciiTest {

	
	@Test
	public void IntegerTo(){
		int val=Integer.valueOf('a');
		int u00a0=Integer.valueOf('\u00a0');
		System.err.println(val);
		System.err.println(u00a0);
	}
	@Test
	public void propertiesTest(){
		
		ResourceManager rm=ResourceManager.getInstance("com.icc.ascii.price2");
		String price2=rm.getString("price2");
		System.err.println(price2);
		System.err.println(price2.length());
	}
	
	@Test
	public void uooao(){
		String str="25000元/月  ";
	}
}
