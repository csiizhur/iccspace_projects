package test;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.junit.Test;

public class SecTest {

	@Test
	public static String test1(){
		if("2"=="2"){
			System.err.println(1);
			return "a";
		}else{
			System.err.println(3);
			//return "we";
		}
		System.err.println(2);
		return "b";
	}
	
	public static void main(String[] args) {
		String str=test1();
		System.err.println(str);
	}
}
