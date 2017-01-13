package test;

import java.math.BigDecimal;

import org.junit.Test;

public class SystemTest {

	public static void main(String[] args) {
		String s=System.getProperty("catalina.base");
		System.err.println(s);
		String s2=System.getProperty("CATALINA_HOME");
		System.err.println(s2);
		String s3=System.getProperty("mm");
		System.err.println(s3);
		
		//Properties p=System.getProperties();
		//System.err.println(p);
		String str="15年3月";
		String [] str1=str.split("年");
		String [] str2=str1[1].split("月");
		System.err.println(str1[0]);
		System.err.println(str2[0]);
		
		String[] str11 = "0年2月".split("年");
		String[] str22 = str11[1].split("月");
		BigDecimal count = BigDecimal.valueOf(Long.parseLong(str11[0]) * 12 + (Integer.parseInt(str22[0])));
		BigDecimal total = count.multiply(new BigDecimal(12));
		System.err.println(total);;
	}
	
	@Test
	public void queryOS() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			System.out.println(os + " can't gunzip");
		}
	}
}
