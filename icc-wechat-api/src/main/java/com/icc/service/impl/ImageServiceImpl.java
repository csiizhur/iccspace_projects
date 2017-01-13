package com.icc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icc.common.WechatDictionary;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.dto.ImageDto;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {

	@Resource
	private ShopsPhotosInfoDao imageDao;
	
	@Override
	public ImageDto create(ShopsPhotosInfo file) {
		
		ImageDto imageDto=new ImageDto(WechatDictionary.IMG_RESULT_SUCCESS, WechatDictionary.IMG_RESULT_SUCCESS_DESC);
		try{
			
			imageDao.create(file);
		}catch (Exception e) {
			imageDto.setRespCode(WechatDictionary.ERROR_CODE_99);
			imageDto.setRespDesc(WechatDictionary.ERROR_CODE_99_DESC);
		}
		return imageDto;
	}

	@Override
	public ImageDto remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageDto update(ShopsPhotosInfo file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShopsPhotosInfo read(String id) {
		// TODO Auto-generated method stub
		return imageDao.read(id);
	}

}
