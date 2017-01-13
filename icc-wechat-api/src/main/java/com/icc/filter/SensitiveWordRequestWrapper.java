package com.icc.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.icc.util.SensitiveWordUtil;

/**
 * 从properties文件中读取的key--value的形式都存放到一个map对象中，方法都用static关键字，方便调用。
    然后写了个KeyWordRequestWrapper来继承HttpServletRequestWrapper，实现包装request对象的作用
 * @description
 * @author zhurun
 * @date 2016年11月18日下午2:21:00
 */
public final class SensitiveWordRequestWrapper extends HttpServletRequestWrapper{
	
	public Map keyMap;
	
	public SensitiveWordRequestWrapper(HttpServletRequest servletRequest,Map keyMap){
		super(servletRequest);
		this.keyMap = keyMap;
	}
	
	@Override
	public Map getParameterMap() {
		super.getContextPath();
		Map<String,String[]> map = super.getParameterMap();
		if(!map.isEmpty()){
			Set<String> keySet = map.keySet();
			Iterator<String> keyIt = keySet.iterator();
			while(keyIt.hasNext()){
				String key = keyIt.next();
//				String value = map.get(key)[0];
//				map.get(key)[0] = this.replaceParam(value);
				//这边实现对整个数组的判断。
				String[] values=map.get(key);
				for(int i=0;i<values.length;i++){
					map.get(key)[i]=this.replaceParam(values[i]);
				}
			}
		}
		return map;
	}

	/**
	 * 框架的request都是使用的getParameterValues来获取的
	 */
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		String[] resources = super.getParameterValues(name); 
		if (resources == null) 
		return null; 
		int count = resources.length; 
		String[] results = new String[count]; 
		for (int i = 0; i < count; i++) { 
			results[i] = this.replaceParam(resources[i]); 
		} 
		return results; 
	}

	public String replaceParam(String name){
		return SensitiveWordUtil.replaceCheck(keyMap,name);
	}
}
