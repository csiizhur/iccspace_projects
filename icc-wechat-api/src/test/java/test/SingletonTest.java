package test;

import org.junit.Test;

public class SingletonTest {

	public  ThreadLocal<String> str;
	
	@Test
	public void lastIndexOf(){
		int result="UserWechatDto com.icc.service.WechatOauthService.getUserInfoByModelMap(ModelMap,UserWechatDto)".lastIndexOf("updateForOptimisticLocking");
		int result1="UserWechatDto com.icc.service.WechatOauthService.getUserInfoByModelMap(ModelMap,UserWechatDto)".lastIndexOf("We");
		
		System.err.println("bca".lastIndexOf("d"));
		if("dca".lastIndexOf("d")!=-1){
			System.err.println("===");
		}
		System.err.println(result);
		System.err.println(result1);
	}
}
