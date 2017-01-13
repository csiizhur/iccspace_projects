package com.icc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.common.WechatDictionary;
import com.icc.dto.ImageDto;
import com.icc.entity.BaseEntity;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.ImageService;
import com.icc.service.ImageUploadService;

@RequestMapping("/image")
@Controller
public class ImageUploadController  {

	@Resource
	private ImageService imageService;
	@Resource
	private ImageUploadService imageUploadService;
	
	@RequestMapping(method=RequestMethod.POST,value="/upload")
	public @ResponseBody JSON imageUpload(HttpServletRequest request,HttpServletResponse response){
		JSONObject rep=new JSONObject();
		ShopsPhotosInfo image=new ShopsPhotosInfo();
		
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){   
        	//转换成多部分request    
        	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
        	//判断上传文件个数
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
            	JSONObject result= imageUploadService.uploadImagesBySpringMVC(multiRequest,iter);
            	
            	if(result.getString(Constants.IMAGE_URL)!=null){
            		image.setImageURL1(result.getString(Constants.IMAGE_URL));
            		 ImageDto imageDto=imageService.create(image);
            		 if(WechatDictionary.IMG_RESULT_SUCCESS.equals(imageDto.getRespCode())){
            			 rep.put("message", "ok");
            		 }else if(WechatDictionary.ERROR_CODE_99.equals(imageDto.getRespCode())){
            			 rep.put("message", "error");
            		 }else{
            			 rep.put("message", "error"); 
            		 }
            	}
            }
              
        }  
        return rep;
	}
	/**
	 * 图片上传 数据库不存储 返回json url
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(method=RequestMethod.POST,value="/uploadMultipartImage")
	public @ResponseBody String imageUploadMultipartFile(HttpServletRequest request,HttpServletResponse response){
		JSONObject rep=new JSONObject();
		BaseEntity be=new BaseEntity();
		Map<String,Object> map=new HashMap<String,Object>();
		List<String> imageUrlList=new ArrayList<String>();
		List<String> imageNameList=new ArrayList<String>();
		
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
		if(multipartResolver.isMultipart(request)){   
			//转换成多部分request    
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			//判断上传文件个数
			//取得request中的所有文件名  
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				JSONObject result= imageUploadService.uploadImagesBySpringMVC(multiRequest,iter);
				
				if(result.getString(Constants.IMAGE_URL)!=null){
					imageUrlList.add(result.getString(Constants.IMAGE_URL));
				}
				if(result.getString(Constants.IMAGE_NAME)!=null){
					imageNameList.add(result.getString(Constants.IMAGE_NAME));
				}
			}
			map.put("imageUrlList", imageUrlList);
			map.put("imageNameList", imageNameList);
			be.setBody(map);
			
			be.getHead().put(Constants.CODE, WechatDictionary.IMG_RESULT_SUCCESS);
			be.getHead().put(Constants.MESSAGE, WechatDictionary.IMG_RESULT_SUCCESS_DESC);
		}else{
			be.getHead().put(Constants.CODE, WechatDictionary.ERROR_CODE_99);
			be.getHead().put(Constants.MESSAGE, WechatDictionary.ERROR_CODE_99_DESC);
		}
		return rep.toJSONString(be);
	}
	
}
