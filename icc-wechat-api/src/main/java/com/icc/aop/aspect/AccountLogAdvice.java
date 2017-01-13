package com.icc.aop.aspect;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.icc.common.Constants;
import com.icc.dao.EntityAccessLogDao;
import com.icc.entity.EntityAccessLog;
import com.icc.util.StrUtil;

/**
 * 
 * @description 记录日志
 * @author zhurun
 * @date 2016年9月30日下午2:53:09
 */
public class AccountLogAdvice {

	private static final String PREFIX_GET = "get";
	private static final String ARG_NAME = "argName";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	private static final String EQUAL_SIGN = "=";
	private static final String SPLIT_SIGN = "|";
	private final static String COMMA_SIGN = ",";
	private final static String COLON_SIGN = ":";
	private final static String OPEN_BRACE = "{";
	private final static String CLOSE_BRACE = "}";
	
	private final static String BASE_TYPE_VALUE = "TYPE";
	private final static String IGNORE_FIELD = "serialVersionUID";
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EntityAccessLogDao entityAccessLogDao;
	
	public void doAfterRecordLog(JoinPoint jp){
	
		try{
			String methodName = jp.getSignature().getName();
			Object[] args = jp.getArgs();
			if(StrUtil.isValidity(methodName) && (methodName.startsWith("query") || methodName.startsWith("query")))
				return ;
			Class<?>[] interfaceClasses = jp.getTarget().getClass().getInterfaces();
			if(interfaceClasses != null && interfaceClasses.length > 0 ){
				String interfaceName = interfaceClasses[0].getName();
				EntityAccessLog ea = new EntityAccessLog();
				ea.setCreateBy(Constants.DEFAULT_CREATER_UPDATER);
				ea.setMethodName(methodName);
				ea.setOperationTime(new Date());
				ea.setOperator("");
				ea.setParamsContent(formatArgs(args));
				ea.setServiceName(interfaceName);
				entityAccessLogDao.create(ea);
				
				logger.info("service_name==>"+interfaceName);
			}
		}catch(Throwable te){
			logger.info("日志异常");
		}
	}
	
	private String formatArgs(Object[] args){
		StringBuilder sb = new StringBuilder(OPEN_BRACKET);
		Object o = null;
		try {
		if(args != null && args.length > 0){
			for(int i=0;i<args.length;i++){
				if(!StrUtil.equals(sb,OPEN_BRACKET))
					sb.append(SPLIT_SIGN);
				o = args[i];
				sb.append(ARG_NAME).append(OPEN_BRACKET).append(i).append(CLOSE_BRACKET).append(EQUAL_SIGN);
				if (o!=null) {
					if ((o.getClass().isPrimitive())) {
						sb.append(o.toString());
					} else if (isWrapClass(o.getClass())) {
						sb.append(o.toString());
					} else if (o instanceof String) {
						sb.append(o.toString());
					} else if (o instanceof Serializable) {
						sb.append(OPEN_BRACE).append(formatObject(o)).append(CLOSE_BRACE);
					} else if (o instanceof List) {

					} else if (o instanceof Map) {

					} else {
						sb.append(OPEN_BRACE).append(formatObject(o)).append(CLOSE_BRACE);
					} 
				}
			}
		}
		sb.append(CLOSE_BRACKET);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			sb.toString();
		} catch (SecurityException e) {
			e.printStackTrace();
			sb.toString();
		}
		return sb.toString();
	}
	
	/**
	 * 获取EntityObject对象的字段值，并拼成格式如：field1:value1,field2:value2....的格式返回
	 * @param eo
	 * @return
	 */
	private String formatObject(Object eo){
		StringBuilder sb = new StringBuilder();
		Object value = null;
		if(eo != null){
			Field[] fields = eo.getClass().getDeclaredFields();
			for(Field f : fields){
				if(IGNORE_FIELD.equals(f.getName()))
					continue;
				if(StrUtil.isValidityStrBuilder(sb))
					sb.append(COMMA_SIGN);
				value = callGetMethod(eo,f.getName());
				sb.append(f.getName()).append(COLON_SIGN).append(value == null ? null : value.toString());
			}
		}
		return sb.toString();
	}

	/**
	 * 执行对象指定字段的get方法，返回get方法返回的值
	 * @param model
	 * @param fieldName
	 * @return
	 */
	private Object callGetMethod(Object model,String fieldName){
		String methodName = new StringBuilder().append(PREFIX_GET).append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1, fieldName.length())).toString();
		try {
			Method m = model.getClass().getMethod(methodName, new Class<?>[0]);
			m.setAccessible(true);
			return m.invoke(model, new Object[0]);
		} catch (SecurityException e) {
			return "";
		} catch (NoSuchMethodException e) {
			return "";
		} catch (InvocationTargetException e){
			return "";
		} catch (IllegalAccessException e){
			return "";
		}
	}
	
	/**
	 * 判断是否是基本类型的包装类
	 * @param clz
	 * @return
	 */
	public static boolean isWrapClass(Class<?> clz) { 
        try { 
           return ((Class<?>) clz.getField(BASE_TYPE_VALUE).get(null)).isPrimitive();
        } catch (Exception e) { 
            return false; 
        } 
	 }
}
