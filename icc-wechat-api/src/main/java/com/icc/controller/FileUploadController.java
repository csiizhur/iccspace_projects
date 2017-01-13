package com.icc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icc.entity.BaseEntity;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.ImageService;
import com.icc.service.ImageUploadService;

/**
 * 
 * @description 文件上传controller
 * @author zhurun
 * @date 2016年10月10日下午2:25:09
 */
@Controller
@RequestMapping("/fileupload")
public class FileUploadController  {

	private Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ImageUploadService fileUploadService;
	@Resource
	private ImageService imageService;
	/**
	 * file文件直接上传
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/image")
	public @ResponseBody JSON saveOrUpdate(HttpServletRequest request,MultipartFile file){
		BaseEntity be=new BaseEntity();
		if(!file.isEmpty()){
			ServletContext sc = request.getSession().getServletContext();
	        String dir = sc.getRealPath("/upload");    //设定文件保存的目录
	        
	        String filename = file.getOriginalFilename();    //得到上传时的文件名
	        try {
				FileUtils.writeByteArrayToFile(new File(dir,filename), file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	        
	        log.info("upload over. "+ filename);
	        return (JSON) JSON.toJSON(be);
		}else{
			return (JSON) JSON.toJSON(be);
			
		}
	}
	@RequestMapping(method=RequestMethod.POST,value="/image1")
	public @ResponseBody String uploadImage(HttpServletRequest request,MultipartFile file){
		BaseEntity be=new BaseEntity();
		File f = new File("F:/data/images/"+file.getOriginalFilename());
		     try{
		        FileUtils.copyInputStreamToFile(file.getInputStream(), f);
		     }catch(Exception e){
		         e.printStackTrace();
		     }
		return JSON.toJSONString(be);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/image2")
	public @ResponseBody JSONObject uploadImage2(MultipartFile file){
		JSONObject result=new JSONObject();
		result=fileUploadService.uploadImages(file);
		return result;
	}
	
	/**
	 * 多文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/image3")
	public @ResponseBody JSONObject uploadImageBySpringMVC(HttpServletRequest request){
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            
        	return fileUploadService.uploadImagesBySpringMVC(request);
        	
              
        }  
        return new JSONObject();  
	}
	/**
	 * 接收到图片流
	 * @param file
	 * @return
	 */
	//"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAdiUlEQVR........."
	public @ResponseBody JSONObject uploadHeadSculpture(String file){
		//获取文件格式
        String postfix = file.split("/")[1].split(";")[0];
        //获取图片的Base64码
        String str = file.split(",")[1];
        String url = "";
        Base64 decoder = new Base64();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(str);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            long title = Calendar.getInstance().getTimeInMillis();
            //获取系统路径并设定文件保存的目录
            String dir = "F:/company/upload/";//图片的上传路径，我这里是从工程的配置文件获取的
            String fileName = title + "." + postfix;
            // 生成jpeg图片
            FileUtils.writeByteArrayToFile(new File(dir, fileName), bytes);
        }catch (Exception e) {
			// TODO: handle exception
		}
        return new JSONObject();
	}
	
	public static void main(String[] args) {
		String imgStr=GetImageStr();
		System.err.println(imgStr);
		GenerateImage(imgStr);
	}
	
	 public static String GetImageStr()
	    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	        String imgFile = "F:\\data\\images.JPG\\";//待处理的图片
	        InputStream in = null;
	        byte[] data = null;
	        //读取图片字节数组
	        try 
	        {
	            in = new FileInputStream(imgFile);        
	            data = new byte[in.available()];
	            in.read(data);
	            in.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	        //对字节数组Base64编码
	        Base64 encoder = new Base64();
	        return encoder.encodeAsString(data);//返回Base64编码过的字节数组字符串
	    }
	 
	 public static boolean GenerateImage(String imgStr)
	    {//对字节数组字符串进行Base64解码并生成图片
	        if (imgStr == null) //图像数据为空
	            return false;
	        Base64 decoder = new Base64();
	        try 
	        {
	            //Base64解码
	            byte[] b = decoder.decode(imgStr);
	            for(int i=0;i<b.length;++i)
	            {
	                if(b[i]<0)
	                {//调整异常数据
	                    b[i]+=256;
	                }
	            }
	            //生成jpeg图片
	            String imgFilePath = "f:\\data\\222.jpg";//新生成的图片
	            OutputStream out = new FileOutputStream(imgFilePath);    
	            out.write(b);
	            out.flush();
	            out.close();
	            return true;
	        } 
	        catch (Exception e) 
	        {
	            return false;
	        }
	    }
	 
	 @ResponseBody  
	 @RequestMapping(value = "uploadForm")  
	 public String uploadMethod(@RequestParam("file") MultipartFile file,  
	         HttpServletRequest request, Map<String, Object> map) {  
	   
	     if (!file.isEmpty()) {  
	         try {  
	             Base64 encoder = new Base64();    
	             // 通过base64来转化图片  
	             String data = encoder.encodeAsString(file.getBytes());  
	               
	             ShopsPhotosInfo Img = new ShopsPhotosInfo();  
	             Img.setImageURL1(data);  
	             imageService.create(Img);  
	               
	         } catch (Exception e) {  
	             e.printStackTrace();  
	         }  
	     } else {  
	         map.put("message", "文件为空");  
	         return "errorView";  
	     }  
	     return null;  
	 } 
	 
	    /** 
	     * 取得图片 
	     * @param request 
	     * @param response 
	     */  
	    @RequestMapping("getImg")  
	    public void getImg(HttpServletRequest request,HttpServletResponse response){  
	        String imgId = request.getParameter("imgId");  
	        ShopsPhotosInfo img = imageService.read(imgId);  
	        String data = img.getImageURL1();  
	        Base64 decoder = new Base64();    
	        try {  
	            byte[] bytes = decoder.decode(data);  
	            for (int i = 0; i < bytes.length; ++i) {    
	                   if (bytes[i] < 0) {// 调整异常数据    
	                       bytes[i] += 256;    
	                   }    
	               }  
	            ServletOutputStream out = response.getOutputStream();  
	            out.write(bytes);  
	               out.flush();  
	               out.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }    
	    }  
}
