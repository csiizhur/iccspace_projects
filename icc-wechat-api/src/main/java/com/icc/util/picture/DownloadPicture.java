package com.icc.util.picture;
import java.io.DataInputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;  
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;  
import com.mysql.jdbc.Statement;  
  
public class DownloadPicture {  
  
	private static final String localPath="F:\\company\\ftp\\upload";
    public static void main(String[] args) {  
        DownloadPicture downloadPicture = new DownloadPicture();
        //获取网络url
        ArrayList<DownloadPictureDto> urlList = downloadPicture.readUrlList();
        //获取当前日期目录  定义zip文件名字
        String path=localPath+File.separator+getNowDateStr(File.separator);
        String zipName=getNowDateStr(File.separator).replace("\\", "_");
        //下载
        downloadPicture.downloadPicture2(urlList,path);
        //压缩
        ZipCompressorByAnt zca = new ZipCompressorByAnt(path+"."+zipName+".zip");
        zca.compressExe(path);
        //更新标志
        downloadPicture.updateUrlList();
        System.err.println(zipName);
    	        
    }
    /** 
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地 
     * @param urlList 
     */  
    public void downloadPicture(ArrayList<DownloadPictureDto> urlList) {  
        URL url = null;  
        @SuppressWarnings("unused")
		int imageNumber = 0;  
          
        for (DownloadPictureDto dto : urlList) {  
            try {  
                url = new URL(dto.getPicUrl());  
                DataInputStream dataInputStream = new DataInputStream(url.openStream());  
                String imageName = dto.getPicName() + ".jpg"; 
                //此方法需要事先生成图片文件 null 则报错
                FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath+imageName));  
                byte[] buffer = new byte[1024];  
                int length;  
  
                while ((length = dataInputStream.read(buffer)) > 0) {  
                    fileOutputStream.write(buffer, 0, length);  
                }  
  
                dataInputStream.close();  
                fileOutputStream.close();  
                imageNumber++;  
            } catch (MalformedURLException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    public void downloadPicture2(ArrayList<DownloadPictureDto> urlList,String path) {  
    	URL url = null;  
    	int imageNumber = 0;  
    	
    	for (DownloadPictureDto dto : urlList) {  
    		try {  
    			url = new URL(dto.getPicUrl());
    			//打开连接
    			URLConnection con=url.openConnection();
    			//输入流
    			InputStream is=con.getInputStream();
    			//1K的数据缓冲
    			byte[] bs=new byte[1024];
    			//读取数据长度
    			int len;
    			File dir=new File(path);
    			if (!dir.exists()) {

    				dir.mkdirs();// 创建路径文件夹

    	        }
    			File file=new File(dir.getPath()+File.separator+"/" + dto.getPicName()+".jpg");
    	        OutputStream os = new FileOutputStream(file);// 输出的文件流

    	        while ((len = is.read(bs)) != -1) {// 开始读取

    	            os.write(bs, 0, len);
    	        }

    	        os.close();
    	        is.close();

    			imageNumber++;  
    		} catch (MalformedURLException e) {  
    			e.printStackTrace();  
    		} catch (IOException e) {  
    			e.printStackTrace();  
    		}  
    	}  
    }  
  
    /**
	 * 获取当前日期字符串
	 * 如果一个文件夹下面保存超过1000个文件，就会影响文件访问性能，
	 * 所以上传的文件要分散存储（这个分散策略有多种，不必拘泥于一种。我这里偷懒用了年月日作为目录层级）
	 * @param separator
	 * @return
	 */
	 public static String getNowDateStr(String separator){
	     Calendar now = Calendar.getInstance();
	     int year = now.get(Calendar.YEAR);
	     int month = now.get(Calendar.MONTH)+1;
	     int day = now.get(Calendar.DATE);
	     
	     return year + separator + month + separator + day;
	 }
	 
    /** 
     * 连接mysql数据库，通过查询数据库读取要下载的图片的url列表 
     * @return 
     */  
    public ArrayList<DownloadPictureDto> readUrlList() {  
        ArrayList<DownloadPictureDto> urlList = new ArrayList<DownloadPictureDto>();  
        try {  
            Connection connection = (Connection) JdbcUtil.getConnection();  
            Statement statement = (Statement) connection.createStatement();  
            String sql = "select id,pic_url url from picture where flg=0"; //查询语句换位相应select语句  
            ResultSet resultSet = statement.executeQuery(sql);  
              
            while (resultSet.next()) {  
            	String name=resultSet.getString("id");
                String url = resultSet.getString("url");
                DownloadPictureDto dto=new DownloadPictureDto(name, url);
                urlList.add(dto);  
            }  
              
            JdbcUtil.free(resultSet, statement, connection);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
  
        return urlList;  
    }
    
    /**
     * 下载ok立即执行update全表
     * @return
     */
    public boolean updateUrlList(){
    	try {  
            Connection connection = (Connection) JdbcUtil.getConnection();  
            Statement statement = (Statement) connection.createStatement();  
            String sql = "update picture set flg=1 where flg=0"; //flg标志替换为已经下载
            int result = statement.executeUpdate(sql);  
            JdbcUtil.free(statement, connection);  
            if(result>0){
            	return true;
            }else{
            	return false;
            }
              
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
		return false;
    }
  
}