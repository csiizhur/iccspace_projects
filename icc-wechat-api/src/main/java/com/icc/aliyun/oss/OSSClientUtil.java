package com.icc.aliyun.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.icc.common.message.CHECKMSG;
import com.icc.common.message.ResourceManager;
import com.icc.wechat.exception.ParametersException;

/**
 * 
 * @description oss
 * @author zhurun
 * @date 2016年10月14日下午3:06:24
 */
public class OSSClientUtil {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static final ResourceManager checkmsg = ResourceManager.getInstance("com.icc.common.message.checkmsg");
	
	private static PropertyResourceBundle rb;

	private static final String FILE_NAME = "config/oss";

	private static String endPoint;
	private static String accessKeyId;
	private static String accessKeySecret;
	private static String bucketName;
	private static String fileDir;

	private OSSClient ossClient;

	static {
		rb = (PropertyResourceBundle) ResourceBundle.getBundle(FILE_NAME);
		endPoint = getString("endPoint");
		accessKeyId = getString("accessKeyId");
		accessKeySecret = getString("accessKeySecret");
		bucketName=getString("bucketName");
		fileDir=getString("fileDir");
	}

	public static final String getString(String propertyName) {
		try {
			return rb.getString(propertyName);
		} catch (Exception e) {
			return "";
		}
	}

	public OSSClientUtil() {
		ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}

	/**
	 * 上传图片(url)
	 *
	 * @param url
	 */
	public String uploadImg2Oss(String url) {
		File fileOnServer = new File(url);
		FileInputStream fin;
		String name = null;
		try {
			fin = new FileInputStream(fileOnServer);
			String[] split = url.split("/");
			this.uploadFile2OSS(fin, split[split.length - 1]);
			name=split[split.length - 1];
		} catch (FileNotFoundException e) {
			throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.UPLOAD_SHOPSPHOTO_2OSS_FAIL, url));
		}
		return name;
	}

	/**
	 * 图片上传(文件)
	 * @param file
	 * @return
	 */
	public String uploadImg2Oss(MultipartFile file) {
		if (file.getSize() > 1024 * 1024) {
			// throw new ImgException("上传图片大小不能超过1M！");
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		Random random = new Random();
		String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
		try {
			InputStream inputStream = file.getInputStream();
			this.uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			// throw new ImgException("图片上传失败");
			return null;
		}
	}

	/**
	 * 获得图片路径
	 *
	 * @param fileUrl
	 * @return
	 */
	public String getImgUrl(String fileUrl) {
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			return this.getUrl(this.fileDir + split[split.length - 1]);
		}
		return null;
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 *
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, fileDir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg") || FilenameExtension.equalsIgnoreCase("jpg")
				|| FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx") || FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx") || FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * 获得url链接
	 *
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

	public static void main(String[] args) {
		/*OSSClient oss = new OSSClient(getString("endPoint"), getString("accessKeyId"), getString("accessKeySecret"));

		for (Bucket bucket : oss.listBuckets()) {
			System.err.println(bucket.getName());
		}

		// Object 是否存在
		boolean found = oss.doesObjectExist("test-iccspace", "LTAI0Xo0GGlD35yY");

		System.err.println(found);
		// url
		System.err.println(new OSSClientUtil().getUrl("LTAI0Xo0GGlD35yY"));
		
		
		oss.shutdown();*/
		
		OSSClientUtil oss= new OSSClientUtil();
		String name=oss.uploadImg2Oss("F:\\company\\Administrator\\iccspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\icc-wechat-api\\upload\\2016\\10\\13\\16/1.png");
		String url=oss.getImgUrl(name);
		System.err.println(url);
	}
}
