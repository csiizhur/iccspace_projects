package com.zhur.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.icc.util.DateUtil;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;

public class Test1 {  
    static Map<Character, Integer> map =new HashMap<>();  
    static Set<String> set =new HashSet<>();  
    public static void PrintMap(){  
        Set<Character> set2=map.keySet();  
        Iterator<Character> iterator=set2.iterator();  
        while(iterator.hasNext()){  
            char xx=iterator.next();  
            System.out.println(xx+" "+map.get(xx));  
        }  
    }  
    public static void main(String []args){  
        char ch[]=new char[100];  
    //  Scanner sc=new Scanner(System.in);  
        String s="hellowword";  
        ch=s.toCharArray();  
  
        for(int j=0;j<ch.length;j++){  
              
            if(map.containsKey(ch[j])){  
                int x=map.get(ch[j]);  
                x++;  
                map.put(ch[j],x);  
                  
            }else{  
                map.put(ch[j],1);  
                  
            }  
        }  
          
      
          
        PrintMap();  
  
          
          
          
          
          
    }  
      
       
    @Test
    public void testTime() throws IOException{
    	List<String> lis=new ArrayList<String>();
    	System.err.println(lis);
    	if(lis!=null){
    		System.err.println(lis);
    	}
    	if(lis.size()==0){
    		System.err.println(lis);
    	}
    	String s="http://www.iccspace.cn/upload/"+"d:\\upload\\pic\\2016\\11\\17\\14\\s.txt".split("pic")[1];
    	
    	System.err.println(new Date().getTime());//1479364526455
    	
    	System.err.println(Calendar.getInstance().getTimeInMillis());//1479364526516
    	
    	String dir = PropertiesUtil.getString("image.local.path");// 图片的上传路径，我这里是从工程的配置文件获取的
		Long time=Calendar.getInstance().getTimeInMillis();
		String uri=PictureUtil.generateFolderPathByTime(time);
		File dirPath=new File(dir+uri);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		String str="qwe";
		/*File file=new File(dirPath, "s.txt");
		FileInputStream fi=new FileInputStream(file);
		fi.read(str.getBytes());*/
		
		FileUtils.writeByteArrayToFile(new File(dirPath, "s.txt"), str.getBytes());
    }
  
    @Test
    public void test2(){
    	int i=0;
    	int a=0;
    	String url="sdf=="+(i+a);
    	for(int j=1;j<5;j++){
    		a++;
    		url=url;
    		System.err.println(url);
    	}
    	
    	long t=Calendar.getInstance().getTimeInMillis();
    	System.err.println(t);
    	System.err.println(System.currentTimeMillis());
    }
    
    @Test
    public void testTimestamp(){
    	Calendar c=Calendar.getInstance();
    	c.add(Calendar.MONTH, -1);
    	Timestamp time=new Timestamp(c.getTimeInMillis());
    	if(time.after(new Timestamp(new Date().getTime()))){
    		System.err.println("==超出一个月");
    	}
    	
    	if(time.before(new Timestamp(new Date().getTime()))){
    		System.err.println("==一个月内的消息");
    	}
    }
    
    @Test
    public void testRexg(){
    	String name= "aa1245sfsdfadsdf";
		Pattern p = Pattern.compile("[\\d]");
    	Matcher matcher = p.matcher(name);
    	String resultName = matcher.replaceAll("-");
    	
    	if(resultName.contains("-")){
    		resultName=resultName.split("-")[0];
    	}
    	System.err.println(resultName);
    }
    @Test
    public void testMap(){
    	Map<String,String> map=new HashMap<String,String>();
    	List<Map<String,String>> l=new ArrayList<Map<String,String>>();
    	map.put("name", "as");
    	l.add(map);
    	//map.clear();
    	map.put("name", "dsa");
    	l.add(map);
    	System.err.println(map);
    	System.err.println(l);
    }
    @Test
    public void testUrl(){
    	String str="http://www.iccspace.cn/tenant/fabu/qiuzufabu.html";
    	String str1="http%3A%2F%2Fwww.iccspace.cn%2Ftenant%2Ffabu%2Fqiuzufabu.html";
    	str1=URLDecoder.decode(str1);
    	System.err.println(str1);
    }
    @Test
    public void testList(){
    	List<String> s=(List<String>) JSONObject.parse("['a','df']");
    	List<String> s2=(List<String>) JSONObject.parse("[]");
    	List<String> l=new ArrayList<String>();
    	l=s;
    	System.err.println(s);
    	System.err.println(l);
    	System.err.println(l.addAll(s));
    	System.err.println(l);
    }
    @Test
    public void testExp(){
    	Date d=new Date(1481252302);
    	Date d2=new Date(1481080670);
    	String s=DateUtil.dateToString(d, "MEDIUM");
    	System.err.println(s);
    }
    @Test
    public void testBigDecimal(){
    	BigDecimal d = new BigDecimal(0);
    	d.multiply(null);
    }
} 