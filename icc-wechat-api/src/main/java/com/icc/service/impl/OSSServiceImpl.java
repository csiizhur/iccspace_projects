package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.aliyun.oss.OSSClientUtil;
import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.service.OSSService;
import com.icc.util.PictureUtil;

@Service
public class OSSServiceImpl implements OSSService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * urlPath
	 */
	public String uploadImage(String imgName){
		
		OSSClientUtil oss=new OSSClientUtil();
		
		//获取本地正式路径
		String timePath=PictureUtil.getImgUrl(imgName,"upload");
		//上传
		String name=oss.uploadImg2Oss(timePath);
		//返回url
		return oss.getImgUrl(name);
	}

	
	public static void main(String[] args) {
		//new OSSServiceImpl().upload("iccspace测试字节");
		System.err.println(PictureUtil.getImgUrl("1.png","temp"));
		System.err.println(PictureUtil.getImgUrl("1476346877198.png","upload"));
	}


	@Override
	public User uploadHeadImage(String localPath,String userId) {
		OSSClientUtil oss=new OSSClientUtil();
		String name=oss.uploadImg2Oss(localPath);
		String oss_url=oss.getImgUrl(name);
		User u=userDao.getUserInfoByUserId(userId);
		u.setHeadImg(oss_url);
		return u;
	}


	@Override
	public String uploadUserHeadImage(String localPath, String userId) {
		OSSClientUtil oss=new OSSClientUtil();
		String name=oss.uploadImg2Oss(localPath);
		String oss_url=oss.getImgUrl(name);
		return oss_url;
	}
}
