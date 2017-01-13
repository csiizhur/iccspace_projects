package com.icc.util.picture;

import java.io.Serializable;

public class DownloadPictureDto implements Serializable {

	private static final long serialVersionUID = -7117181726627053115L;

	private String picName;
	private String picUrl;
	
	public DownloadPictureDto(String picName,String picUrl) {
		this.picName=picName;
		this.picUrl=picUrl;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}
