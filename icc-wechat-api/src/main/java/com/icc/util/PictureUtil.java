package com.icc.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @description 图片 文件工具
 * @author zhurun
 * @date 2016年10月13日上午10:21:08
 */
public class PictureUtil {
    
    /**
     * 获取图片URL
     * 
     * @param imgName
     * @param prifix
     * @return
     */
    public static String getImgUrl(String imgName,String tempOrUpload) {
    	if (!StrUtil.isValidity(imgName)) {
			return PropertiesUtil.getString("default_images_url");
		}
		String[] pic = imgName.split("\\.");
		if (pic.length < 2) {
			return PropertiesUtil.getString("default_images_url");
		}
		String imgUrl;
		if (tempOrUpload == "upload") {
			imgUrl = getFolderPath(PropertiesUtil.getString("image.local.path") + "/", imgName) + imgName;
		} else if(tempOrUpload=="temp"){
			imgUrl = getFolderPath(PropertiesUtil.getString("image.local.temp.path") + "/", imgName) + imgName;

		}else{
			throw new RuntimeException();
		}
		return imgUrl;
    }

    /**
     * 获取图片磁盘地址
     * 
     * @param path
     * @param imgName
     * @return
     */
    public static String getImgFilePath(String path, String imgName) {
        if (StrUtil.isValidity(imgName)) {
            return "";
        }
        return getFolderPath(path, imgName) + imgName;
    }
   
    /**
     * 获取文件磁盘目录
     * 
     * @param path
     * @param imgName
     * @return
     */
    public static String getFolderPath(String path, String imgName) {
        String[] pic = imgName.split("\\.");
        if (pic.length < 2 ) {
            return "";
        }
        return path + generateFolderPathByTime(Long.valueOf(pic[0]));
    }
    /**
     * ds /** 根据时间生成目录
     * 
     * @param time
     * @return
     */
    public static String generateFolderPathByTime(long time) {
        if (time <= 0) {
            return "";
        }
        return "/" + getYear(time) + "/" + getMonth(time) + "/" + getDate(time) + "/"
                + getHour(time) + "/";
    }
    /**
     * 获取年份
     * 
     * @param millis long
     * @return int
     */
    public static final int getYear(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * 
     * @param millis long
     * @return int
     */
    public static final int getMonth(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期
     * 
     * @param millis long
     * @return int
     */
    public static final int getDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取小时
     * 
     * @param millis long
     * @return int
     */
    public static final int getHour(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    /**
	 * 获取当前日期字符串
	 * 如果一个文件夹下面保存超过1000个文件，就会影响文件访问性能，
	 * 所以上传的文件要分散存储（这个分散策略有多种，不必拘泥于一种。我这里偷懒用了年月日作为目录层级）
	 * @param separator
	 * @return
	 */
	 public static String generateFolderPathByTime(Long path,String separator){
	     Calendar now = Calendar.getInstance();
	     int year = now.get(Calendar.YEAR);
	     int month = now.get(Calendar.MONTH)+1;
	     int day = now.get(Calendar.DATE);
	     int hour =now.get(Calendar.HOUR);
	     
	     return year + separator + month + separator + day + separator + hour;
	 }
	 /**
	  * 图片转换字节流
	  * @param filePath
	  * @return
	  */
	 public static byte[] getBytes(String filePath){
			byte[] buffer = null;
			try {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
				byte[] b = new byte[1000];
				int n;
				while ((n = fis.read(b)) != -1) {
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buffer;
		}
	 public static void main(String[] args) {
		 long time = new Date().getTime();
		 String imageName = time + ".png";//1476327381043.png
		 System.err.println(imageName);
		 System.err.println(generateFolderPathByTime(time));
		 
		 String newPhoto="1410858895857=8";
		 Long id = Long.parseLong(newPhoto.substring(newPhoto.indexOf("=")+1));
		 System.err.println(""+id);
		 
		 String oldPath=getImgUrl("1476336898838.xls","2");
		 System.err.println();
		 String newPath=PropertiesUtil.getString("image.local.path")+oldPath.substring(oldPath.lastIndexOf("upload")+6);
		 System.err.println(newPath);
		 
		 String str=newPath.substring(0, newPath.lastIndexOf("/"));
		 System.err.println(str);
		 FilesUtil.createFolder(str);
		 FilesUtil.moveFile(oldPath, newPath);
	}
}
