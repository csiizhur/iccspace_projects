package com.icc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.service.ImageUploadService;
import com.icc.util.PictureUtil;

@Service
public class ImageUploadSereviceImpl implements ImageUploadService {

	private static final String allowSuffixs="gif,jpg,jpeg,bmp,png,ico";
	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Override
	public JSONObject uploadImages(MultipartFile file) {
		JSONObject params=new JSONObject();
		// TODO 过滤合法的文件类型
		String fileName=file.getOriginalFilename();
		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
		
		if(allowSuffixs.indexOf(suffix)==-1){
			params.put("result", "not support the file type!");
			return params;
		}
		try {
			// TODO 获取网络地址、本地地址头部
			Properties config = new Properties();
			config.load(this.getClass().getClassLoader().getResourceAsStream("/config/url.properties"));
			String urlPath = config.getProperty("imageUrlRoot");
			String localPath = config.getProperty("imageUploadPath");
			
			// TODO 创建新目录
			//String uri=File.separator+getNowDateStr(File.separator);
			Long time=new Date().getTime();
			String uri=PictureUtil.generateFolderPathByTime(time);
			File dir=new File(localPath+uri);
			if(!dir.exists()){
				dir.mkdirs();
			}
			// TODO 创建新文件
			//String newFileName=getUniqueFileName();
			String newFileName=time+"";
			File f=new File(dir.getPath()+File.separator+newFileName+"."+suffix);
			
			// TODO 将输入流中的数据复制到新文件
			FileUtils.copyInputStreamToFile(file.getInputStream(), f);
			params.put("code", "ok");
			params.put("message", "upload success");
			params.put("imageURL", urlPath + uri.replace("\\", "/") + "/" + newFileName + "." + suffix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return params;
	}

	
	/**
	 * 获取当前日期字符串
	 * 如果一个文件夹下面保存超过1000个文件，就会影响文件访问性能，
	 * 所以上传的文件要分散存储（这个分散策略有多种，不必拘泥于一种。我这里偷懒用了年月日作为目录层级）
	 * @param separator
	 * @return
	 */
	 public static String getNowDateStr(String separator){
	     Calendar now = Calendar.getInstance();
	     int year = now.get(Calendar.YEAR);
	     int month = now.get(Calendar.MONTH)+1;
	     int day = now.get(Calendar.DATE);
	     int hour =now.get(Calendar.HOUR_OF_DAY);
	     
	     return year + separator + month + separator + day + separator + hour;
	 }
	
	 /**
	  * 生成唯一的文件名
	  * @return
	  */
	  public static String getUniqueFileName(){
	      String str = UUID.randomUUID().toString();
	      return str.replace("-", "");
	  }


	@Override
	public JSONObject uploadImagesBySpringMVC(HttpServletRequest request) {
		
		JSONObject result =new JSONObject();
		//转换成多部分request    
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
        //取得request中的所有文件名  
        Iterator<String> iter = multiRequest.getFileNames();  
        while(iter.hasNext()){  
            //记录上传过程起始时的时间，用来计算上传时间  
            int pre = (int) System.currentTimeMillis();  
            //取得上传文件  
            MultipartFile file = multiRequest.getFile(iter.next());  
            if(file != null){  
                //取得当前上传文件的文件名称  
                String fileName = file.getOriginalFilename();
                String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                if(fileName.trim() !=""){  
                    //定义上传路径  
                    // TODO 创建新目录
                    Properties config = new Properties();
        			try {
						config.load(this.getClass().getClassLoader().getResourceAsStream("/config/url.properties"));
					
						String urlPath = config.getProperty("imageUrlRoot");
						String localPath = config.getProperty("imageUploadPath");
						String uri=File.separator+getNowDateStr(File.separator);
						File dir=new File(localPath+uri);
						if(!dir.exists()){
							dir.mkdirs();
						}
						String newFileName=getUniqueFileName();
						File f=new File(dir.getPath()+File.separator+newFileName+"."+suffix); 
        			
						file.transferTo(f);
						
						result.put("imageURL", urlPath+uri.replace("\\", "/")+"/"+newFileName+"."+suffix);
        			} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
                    
                }  
            }  
            //记录上传该文件后的时间  
            int finaltime = (int) System.currentTimeMillis();  
            log.info("上传耗时==》"+(finaltime - pre));  
        }
		return result;
	}


	/**
	 * 将上传的路径 和文件名称返回
	 */
	@Override
	public JSONObject uploadImagesBySpringMVC(MultipartHttpServletRequest multiRequest, Iterator<String> iter) {
		JSONObject result=new JSONObject();
		//记录上传过程起始时的时间，用来计算上传时间  
        int pre = (int) System.currentTimeMillis();  
        //取得上传文件  
        MultipartFile file = multiRequest.getFile(iter.next());  
        if(file != null){  
            //取得当前上传文件的文件名称  
            String fileName = file.getOriginalFilename();
            String suffix=fileName.substring(fileName.lastIndexOf(".")+1);
            //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
            if(fileName.trim() !=""){  
                //定义上传路径  
                // TODO 创建新目录
                Properties config = new Properties();
    			try {
					config.load(this.getClass().getClassLoader().getResourceAsStream("/config/url.properties"));
				
					String urlPath = config.getProperty("imageUrl");
					String localPath = config.getProperty("image.local.temp.path");
					Long time=new Date().getTime();
					String uri=PictureUtil.generateFolderPathByTime(time);
					File dir=new File(localPath+uri);
					if(!dir.exists()){
						dir.mkdirs();
					}
					String newFileName=time+"";
					File f=new File(dir.getPath()+File.separator+newFileName+"."+suffix); 
    			
					file.transferTo(f);
					
					result.put(Constants.IMAGE_URL, urlPath+uri.replace("\\", "/")+"/"+newFileName+"."+suffix);
					result.put(Constants.IMAGE_NAME,newFileName+"."+suffix);
					result.put("message", "upload success");
    			} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					result.put(Constants.CODE, "");
					result.put(Constants.MESSAGE, "upload fail");
				}
                
            }  
        }  
        //记录上传该文件后的时间  
        int finaltime = (int) System.currentTimeMillis();  
        log.info("上传耗时==》"+(finaltime - pre));
		return result;
	}
}
