package com.icc.dto;

import java.io.Serializable;

import com.icc.aop.aspect.BaseEntity;
import com.icc.entity.ShopsPhotosInfo;

public class ImageDto extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 126643293229370085L;

	private ShopsPhotosInfo img;
	
	public ImageDto(String code,String description) {
		super(code,description);
	}

	public ShopsPhotosInfo getImg() {
		return img;
	}

	public void setImg(ShopsPhotosInfo img) {
		this.img = img;
	}
	
	
}
