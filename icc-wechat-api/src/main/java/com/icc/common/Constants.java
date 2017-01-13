package com.icc.common;

/**
 * 
 * @description 常量的定义
 * @author zhur
 * @date 2016年9月19日上午11:35:58
 */
public class Constants {

	
	public static final int RELEASE_SHOPS_TYPE_LEASE=1;//出租
	public static final int RELEASE_SHOPS_TYPE_RENT=2;//求租
	public static final int USER_ROLE_LEASE=1;
	public static final int USER_ROLE_RENT=2;
	
	public static final int DB_VALUE_NOTNULL=0;//数据库int型字段默认值为0的情况
	public static final int DB_VALUE_NOTDELETE=0;
	public static final int DB_VALUE_ISDELETE=1;
	
	public static final String CODE="code";
	public static final String MESSAGE="message";
	
	public static final int SIZE_0=0;
	
	public static final Integer pageNum=1;
	public static final Integer pageSize=15;
	
	public static final String DEFAULT_CREATER_UPDATER="sys";
	
	public static final String SESSION_USER="session_user";
	
	public static final String IMAGE_URL="image_url";//上传 网路地址
	public static final String IMAGE_NAME="image_name";//上传 文件名称
	
	public static final int OSS_UPLOAD_FLG_1=1;//上传成功
	public static final int OSS_UPLOAD_FLG_2=2;
	
	public static final int RELEASE_SHOPS_TYPE_3=3;//合租
	public static final int RELEASE_SHOPS_TYPE_4=4;//转租
	
	public static final int AFFECTED_ROWS_0=0;//影响行数0
	public static final int AFFECTED_ROWS_1=1;//影响行数1
	
	public static final int QUERY_DB_DATA_COUNT=0;//查询DB记录数
	
	public static final String UPLOAD_SERVER_PATH="http://www.iccspace.cn/upload/";//oss路径不存在时使用的服务器端路径
	
	public static final String WEIXIN_DOWNLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/get";//微信素材下载
}
