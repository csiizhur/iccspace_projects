package com.icc.service;

import com.icc.dto.ImageDto;
import com.icc.entity.ShopsPhotosInfo;

public interface ImageService {

	/**
	 * 添加
	 */
	ImageDto create(ShopsPhotosInfo file);
	/**
	 * 删除
	 */
	ImageDto remove(String id);
	/**
	 * 修改
	 */
	ImageDto update(ShopsPhotosInfo file);
	/**
	 * 获取
	 */
	ShopsPhotosInfo read(String id);
	/**
	 * 列表
	 */
	//ShopsPhotosInfo findList(FileQuery query);
}
