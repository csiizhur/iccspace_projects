package com.icc.aliyun.opensearch;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.aliyun.opensearch.object.KeyTypeEnum;
import com.icc.dao.ShopsHistoryDao;

/**
 * Opensearch Client
 * @description
 * @author zhurun
 * @date 2016年11月8日下午2:37:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class OpenSearchClientUpload {

	private static  String accessKey="LTAI0Xo0GGlD35yY";
	private static  String secret="73ImaOCKfGq2u4wqwko9nMyIoMl3k7";
	private static  String appName="opensearch_iccspace";
	private static String endPoint="http://opensearch-cn-hangzhou.aliyuncs.com";
	
	public void init(String accessKeyId, String accessKeySecret){
		this.accessKey=accessKeyId;
		this.secret=accessKeySecret;
	}
	public void initOpensearch(String endpoint, String appName) throws UnknownHostException{
		Map<String, Object> opts = new HashMap<String, Object>();
        this.appName = appName;
        // 这里的host需要根据访问应用详情页中提供的的API入口来确定
        opts.put("host", endpoint);
        CloudsearchClient openSearchClient = new CloudsearchClient(accessKey, secret,null,opts, KeyTypeEnum.ALIYUN);
	}
	public static OpenSearchClientUpload getInstance() {
        return new OpenSearchClientUpload();
	}
	
	/*public void addData(){
		Map<String, Object> opts = new HashMap<String, Object>();
		// 这里的host需要根据访问应用详情页中提供的的API入口来确定
		CloudsearchClient client = new CloudsearchClient(accessKey, secret , null, opts, KeyTypeEnum.ALIYUN);
		
		CloudsearchDoc doc = new CloudsearchDoc(appName, client);
		String table_name = "shops";//"要上传数据的表名";
		//String data = "[{"cmd":"add", "fields":{"id":"0","summary":"blabla..."}}]";
		System.out.println(doc.push(data, table_name));
	}*/
	@Test
	public void addData() throws ClientProtocolException, IOException{
		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put("host", endPoint);
		CloudsearchClient client = new CloudsearchClient(accessKey, secret , endPoint, opts, KeyTypeEnum.ALIYUN);
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		
		Map<String, Object> ques = new HashMap<String, Object>();
		
		for(int i=0;i<=5;i++){

            ques.put("shopsid", "200003"+i);//ID主键

            ques.put("shopname", "搞网站运营已有6年时间了）");

            //ques.put("content", "搞网站运营已有6年时间了，期间运营过大大小的网站10多个，空间、VPS国内国外都使用过。国内最普遍的环境就是不稳定，隔三差五不是被攻击就是线路调整。一直困惑不已。  ");

            doc.add(ques);

            //每加入1000条数据结束之后push一下

            if(i%1000==0){

                doc.push("shops");

            }

        }
	}
	
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	/**
	 * 数据上传
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
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
	@Test
	public void deleteDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		//doc.remove(fields);
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
	@Test
	public void updateDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		//doc.remove(fields);
		List<Map<String,Object>> que=shopsHistoryDao.queryShopsHistoryForUpdateOpensearch();
		if(que!=null && que.size()!=0){
			for(int i=0;i<que.size();i++){
				doc.update(que.get(i));				
			}
			doc.push("shops");
		}
	}
	/**
	 * 数据详情
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void detailDataJSON() throws ClientProtocolException, IOException{
		CloudsearchClient client = OpensearchUtil.getInstance();
		
		CloudsearchDoc doc=new CloudsearchDoc(appName, client);
		String result=doc.detail("shops");
		System.err.println(result);
	}
}
