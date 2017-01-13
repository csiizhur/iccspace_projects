package com.icc.aliyun.opensearch;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.object.KeyTypeEnum;
/**
 * Opensearch Client
 * @description
 * @author zhurun
 * @date 2016年11月8日下午3:27:45
 */
public class OpensearchUtil {

	private static final String accessKey="LTAI0Xo0GGlD35yY";
	private static final String secret="73ImaOCKfGq2u4wqwko9nMyIoMl3k7";
	private static final String endPoint="http://opensearch-cn-hangzhou.aliyuncs.com";
	
	private static CloudsearchClient client;

    private static Object lock = new Object();
    
	public static CloudsearchClient getInstance() throws UnknownHostException {
		if (client == null) {

			synchronized (lock) {

				if (client == null) {

					Map<String, Object> opts = new HashMap<String, Object>();

					opts.put("host", endPoint);

					client = new CloudsearchClient(accessKey, secret, endPoint, opts, KeyTypeEnum.ALIYUN);

				}

			}
		}
		return client;
	}
	public static CloudsearchClient getInstance(String filter) throws UnknownHostException {
		if (client == null) {
			
			synchronized (lock) {
				
				if (client == null) {
					
					Map<String, Object> opts = new HashMap<String, Object>();
					
					opts.put("host", endPoint);
					if(StringUtils.isNotEmpty(filter)){
						opts.put("filter", filter);
					}
					
					client = new CloudsearchClient(accessKey, secret, endPoint, opts, KeyTypeEnum.ALIYUN);
					
				}
				
			}
		}
		return client;
	}
	
	
}
