package com.icc.common.model.baidu;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "technology_rating" (class com.icc.common.model.baidu.DetailInfo), not marked as ignorable
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3290820524885638255L;

	private int distance;
	private String tag;//房地产;住宅区
	private String type;
	private String detail_url;
	private String overall_rating;
	private String service_rating;
    private String environment_rating;
	private String image_num;
	private String comment_num;
	private String price;
	private List<?> di_review_keyword;
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	public String getService_rating() {
		return service_rating;
	}
	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}
	public String getEnvironment_rating() {
		return environment_rating;
	}
	public void setEnvironment_rating(String environment_rating) {
		this.environment_rating = environment_rating;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<?> getDi_review_keyword() {
		return di_review_keyword;
	}
	public void setDi_review_keyword(List<?> di_review_keyword) {
		this.di_review_keyword = di_review_keyword;
	}
	
}
