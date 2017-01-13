package com.icc.util;

import java.util.Properties;

import com.icc.start.InterfaceUrlInit;

/**
 * 
 * @description properties文件工具类--用来配置全局变量
 * @author zhur
 * @date 2016年9月14日下午5:49:16
 */
public class GlobalConstantsPropertiesUtil {

	public static Properties interfaceUrlProperties;

	static {
        if (GlobalConstantsPropertiesUtil.interfaceUrlProperties == null) {
            InterfaceUrlInit.init();
        }
    }
	
	/*public static String getInterfaceUrl(String key) {
		return (String) interfaceUrlProperties.get(key);
	}*/
	
	/**
     * 
     * @Description: 根据不同类型取值
     * @param @param key
     * @param @return
     * @author dapengniao
     * @date 2015年10月13日 下午4:59:14
     */
    public static String getString(String key) {
        String Properties = (String) interfaceUrlProperties.get(key);
        return Properties == null ? null : Properties;
    }
 
    public static Integer getInt(String key) {
        String Properties = (String) interfaceUrlProperties.get(key);
        return Properties == null ? null : Integer.parseInt(Properties);
    }
 
    public static Boolean getBoolean(String key) {
        String Properties = (String) interfaceUrlProperties.get(key);
        return Properties == null ? null : Boolean.valueOf(Properties);
    }
 
    public static Long getLong(String key) {
        String Properties = (String) interfaceUrlProperties.get(key);
        return Properties == null ? null : Long.valueOf(Properties);
    }
}
