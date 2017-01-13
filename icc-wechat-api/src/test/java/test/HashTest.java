package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.icc.entity.User;

public class HashTest {

	@Test
	public void testHsah(){
		User u=new User();
		System.err.println(u.hashCode());
		System.err.println(u);
	}
	
	@Test
	public void testJson(){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userId", 1001);

		map.put("userName", "ÕÅÈý");

		map.put("userSex", "ÄÐ");
		
		
		System.err.println(JSONObject.toJSON(map).toString());
		
		
		List<User> list = new ArrayList<User>();

		User user = new User();
		user.setAge(10);
		user.setNickName("weee");

		list.add(user);

		list.add(user);

		list.add(user);
		System.err.println(JSONObject.toJSONString(list).toString());
		System.err.println(JSONObject.toJSON(list).toString());
		
		/**
		 * {
    "body": [
        {
            "sysNoticeContent": "",
            "sysNoticeTime": ""
        }
    ],
    "head": {
        "code": "0000",
        "message": "ok"
    }
}
		 */
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> head = new HashMap<String, Object>();
		List<Object> body=new ArrayList<>();
		body.add(user);
		body.add(user);
		head.put("code", 80);
		head.put("message", "ok");
		map1.put("head", head);
		map1.put("body", body);
		
		System.err.println(JSONObject.toJSON(map1)+"<===");
		
	}
}
