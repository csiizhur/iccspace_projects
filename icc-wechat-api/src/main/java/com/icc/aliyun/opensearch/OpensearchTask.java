package com.icc.aliyun.opensearch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.icc.dao.ShopsHistoryDao;

/**
 * Opensearch JOB
 * @description
 * @author zhurun
 * @date 2016年11月8日下午2:37:36
 */
@Component
public class OpensearchTask {

	private static  String accessKey="LTAI0Xo0GGlD35yY";
	private static  String secret="73ImaOCKfGq2u4wqwko9nMyIoMl3k7";
	private static  String appName="opensearch_iccspace";
	private static String endPoint="http://opensearch-cn-hangzhou.aliyuncs.com";
	
	public void init(String accessKeyId, String accessKeySecret){
		this.accessKey=accessKeyId;
		this.secret=accessKeySecret;
	}
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	/*public ShopsHistoryDao getShopsHistoryDao() {
		return shopsHistoryDao;
	}
	public void setShopsHistoryDao(ShopsHistoryDao shopsHistoryDao) {
		this.shopsHistoryDao = shopsHistoryDao;
	}*/
	/**
	 * 数据上传
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	public void addDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		List<Map<String,Object>> que=shopsHistoryDao.queryShopsHistoryForUpdateOpensearch();
		if (que!=null && que.size()!=0) {
			for (int i = 0; i < que.size(); i++) {
				doc.add(que.get(i));
			}
			doc.push("shops");
		}
	}
	/**
	 * 数据删除
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void deleteDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		List<Map<String,Object>> que=shopsHistoryDao.queryShopsHistoryForUpdateOpensearch();
		if(que!=null && que.size()!=0){
			for(int i=0;i<que.size();i++){
				doc.remove(que.get(i));				
			}
			doc.push("shops");
		}
	}
	/**
	 * 数据更新
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void updateDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		//@Autowired null
		List<Map<String,Object>> que=shopsHistoryDao.queryShopsHistoryForUpdateOpensearch();
		/*if(que!=null && que.size()!=0){
			for(int i=0;i<que.size();i++){
				doc.update(que.get(i));				
			}
			doc.push("shops");
		}*/
	}
}
