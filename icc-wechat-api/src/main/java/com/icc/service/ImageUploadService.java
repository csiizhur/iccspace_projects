package com.icc.service;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface ImageUploadService {

	//字节流上传
	public JSONObject uploadImages(MultipartFile file);
	//springmvc包装解析器上传 service层上传多张
	public JSONObject uploadImagesBySpringMVC(HttpServletRequest request);
	//springmvc包装解析器上传 controller层跑多张上传
	public JSONObject uploadImagesBySpringMVC(MultipartHttpServletRequest multiRequest,Iterator<String> ite);
}
