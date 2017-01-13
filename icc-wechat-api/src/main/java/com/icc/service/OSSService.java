package com.icc.service;

import com.icc.entity.User;

public interface OSSService {

	public String uploadImage(String imgName);
	
	//上传本地图象
	public User uploadHeadImage(String localPath,String userId);
	//上传图像，返回url
	public String uploadUserHeadImage(String localPath,String userId);
}
