package com.icc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.util.StringUtils;

import com.icc.wechat.exception.ParametersException;

/** 
 * 说明 
 * 利用httpclient下载文件 
 * maven依赖 
 * <dependency> 
*           <groupId>org.apache.httpcomponents</groupId> 
*           <artifactId>httpclient</artifactId> 
*           <version>4.0.1</version> 
*       </dependency> 
*  可下载http文件、图片、压缩文件 
*  bug：获取response header中Content-Disposition中filename中文乱码问题 
 * @author zhur 
 * 
 */  
public class HttpDownload {  
  
    public static final int cache = 1024 * 1024;  
    public static final boolean isWindows;  
    public static final String splash;  
    public static final String root;  
    public static final String root_headimage;  
    static {  
        if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {  
            isWindows = true;  
            splash = "/";  
            root="D:/test";
            root_headimage="D:/test";
        } else {  
            isWindows = false;  
            splash = "/";  
            root="/mnt/iccspace/upload/pic";  
            root_headimage="/mnt/iccspace/upload/headimage";  
        }  
    }  
      
    /** 
     * 根据url下载文件，文件名从response header头中获取 
     * @param url 
     * @return 
     */  
    public static String download(String url,Map<String,String> params) {  
        return download(url, null,params);  
    }  
  
    /**
     * 根据url下载文件，保存到filepath中 
     * @param url
     * @param filepath
     * @param params
     * @return
     */
    public static String download(String url, String filepath,Map<String,String> params) {  
        try {  
            HttpClient client = new DefaultHttpClient();
            
            url=HttpUtils.buildUrl(url, params);
            HttpGet httpget = new HttpGet(url);  
            HttpResponse response = client.execute(httpget);  
  
            HttpEntity entity = response.getEntity();
            Header contentType=entity.getContentType();
            HeaderElement[] he=contentType.getElements();
            String suff = null;
            for(int i=0;i<he.length;i++){
            	if(he[i].getName().contains("image")){
            		suff=he[i].getName().split("/")[1];
            	}else{
            		suff="html";
            		throw new ParametersException("图片格式不正确");
            	}
            }
            InputStream is = entity.getContent();
            long length=entity.getContentLength();
            if(length<=0){
            	System.err.println("下载文件不存在");
            	return null;
            }
            if(StringUtils.isEmpty(filepath)){
            	long p=Calendar.getInstance().getTimeInMillis();
            	filepath=root+splash+PictureUtil.generateFolderPathByTime(p)+p+"."+suff;
            	
            }else{
            	filepath=root_headimage+splash+filepath+suff;
            }
            File file = new File(filepath);  
            file.getParentFile().mkdirs();  
            FileOutputStream fileout = new FileOutputStream(file);  
            /** 
             * 根据实际运行效果 设置缓冲区大小 
             */  
            byte[] buffer=new byte[cache];  
            int ch = 0;  
            while ((ch = is.read(buffer)) != -1) {  
                fileout.write(buffer,0,ch);  
            }  
            is.close();  
            fileout.flush();  
            fileout.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return filepath;  
    }  
    /** 
     * 获取response header中Content-Disposition中的filename值 
     * @param response 
     * @return 
     */  
    public static String getFileName(HttpResponse response) {  
        Header contentHeader = response.getFirstHeader("Content-Disposition");  
        String filename = null;  
        if (contentHeader != null) {  
            HeaderElement[] values = contentHeader.getElements();  
            if (values.length == 1) {  
                NameValuePair param = values[0].getParameterByName("filename");  
                if (param != null) {  
                    try {  
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");  
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");  
                        filename = param.getValue();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return filename;  
    }  
    
    public static void main(String[] args) {
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("access_token", "SacDH8O_u8vi73yi-uWWs5DCwGiAABS4YSB4uXfBEquKY0p-7KkwB5TQs4FiZepe3SuzYKH43MytRh7HWL9kV2ADolsaJBb9Yf-3Uk2LuzvKSL9oYbqygzBZyqDK-K2yIHBbAHABAU");
    	params.put("media_id", "vyVMuWxXVy5fVaeyVOJ3e73jl_YiTVTspAqadg19hJ6t86z8ff_-M-eaFJTGVaRA");
        //String url="https://api.weixin.qq.com/cgi-bin/media/get";  
    	String url="http://www.iccspace.cn/upload/2016/11/18/9/1479433932579.jpeg";  
    	//String url="http://www.iccspace.cn/upload/zuhuxq.html";  
        String path=HttpDownload.download(url,params);
        System.err.println(path);
    }  
}