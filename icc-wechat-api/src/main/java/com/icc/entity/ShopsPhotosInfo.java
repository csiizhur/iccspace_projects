package com.icc.entity;

import java.io.Serializable;

/**
 * 
 * @description 商铺图片信息
 * @author zhur
 * @date 2016年9月13日上午9:36:26
 */
public class ShopsPhotosInfo implements Serializable {

	private static final long serialVersionUID = -925490437892546206L;

	private String Id;
	private String shopsId;
	private String imageURL1;
	private String imageURL2;
	private String imageURL3;
	private int deleted;
	
	private String imageName;
	private String ossUrl;
	private int ossUploadFlg;
	public ShopsPhotosInfo() {
		// TODO Auto-generated constructor stub
	}
	public ShopsPhotosInfo(String id,String imageURL1) {
		this.Id=id;
		this.imageURL1=imageURL1;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getShopsId() {
		return shopsId;
	}
	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	public String getImageURL1() {
		return imageURL1;
	}
	public void setImageURL1(String imageURL1) {
		this.imageURL1 = imageURL1;
	}
	public String getImageURL2() {
		return imageURL2;
	}
	public void setImageURL2(String imageURL2) {
		this.imageURL2 = imageURL2;
	}
	public String getImageURL3() {
		return imageURL3;
	}
	public void setImageURL3(String imageURL3) {
		this.imageURL3 = imageURL3;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getOssUrl() {
		return ossUrl;
	}
	public void setOssUrl(String ossUrl) {
		this.ossUrl = ossUrl;
	}
	public int getOssUploadFlg() {
		return ossUploadFlg;
	}
	public void setOssUploadFlg(int ossUploadFlg) {
		this.ossUploadFlg = ossUploadFlg;
	}
	
}
