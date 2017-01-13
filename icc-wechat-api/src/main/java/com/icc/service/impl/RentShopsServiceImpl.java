package com.icc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icc.aliyun.oss.OSSClientUtil;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.entity.RentShops;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.RentShopsService;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpDownload;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;

@Service
public class RentShopsServiceImpl implements RentShopsService {

	@Autowired
	private RentShopsDao rentShopsDao;
	@Autowired
	private ShopsPhotosInfoDao shopsPhotosInfoDao;
	@SuppressWarnings("unused")
	@Override
	public HeadMessageDto editReleaseRentShopsByUser(RentShops rs,List<String> imgDatas,List<String> delIds) {
		HeadMessageDto resultDto=new HeadMessageDto();
		
		if (StringUtils.isEmpty(rs.getRentId()) || StringUtils.isEmpty(rs.getRentId())) {
			throw new IllegalArgumentException("求租编号不存在");
		} else if (imgDatas != null) {
			List<String> listPath = new ArrayList<String>();
			if (imgDatas != null && imgDatas.size()!=0) {
				for (int i = 0; i < imgDatas.size(); i++) {
					String base64Data = imgDatas.get(i);
					String postfix = base64Data.split("/")[1].split(";")[0];
					String str = base64Data.split(",")[1];
					Base64 decoder = new Base64();
					byte[] bytes = decoder.decode(str);
					for (int j = 0; j < bytes.length; ++j) {
						if (bytes[j] < 0) {
							bytes[j] += 256;
						}
					}
					long title = Calendar.getInstance().getTimeInMillis();
					// 获取系统路径并设定文件保存的目录
					String dir = PropertiesUtil.getString("image.local.path");// 图片的上传路径，我这里是从工程的配置文件获取的
					String uri=PictureUtil.generateFolderPathByTime(title);
					File dirPath=new File(dir+uri);
					if(!dirPath.exists()){
						dirPath.mkdirs();
					}
					String fileName = title + "." + postfix;
					try {
						FileUtils.writeByteArrayToFile(new File(dirPath, fileName), bytes);
					} catch (IOException e) {
						e.printStackTrace();
					}

					listPath.add(dirPath + fileName);
				}

			}
			List<String> ossUrl = new ArrayList<String>();
			if(listPath!=null){
				OSSClientUtil oss = new OSSClientUtil();
				for (int l = 0; l < listPath.size(); l++) {
					String name = oss.uploadImg2Oss(listPath.get(l));
					String oss_url = oss.getImgUrl(name);
					//如过上传OSS文件找不到，则返回服务器路径
					////mnt/iccspace/upload/pic/2016/11/18/9/1479434108845.jpeg
					//http://www.iccspace.cn/upload/2016/11/18/9/1479434108845.jpeg
					if(oss_url==null){
						if (listPath.get(l).contains("pic")) {
							ossUrl.add(Constants.UPLOAD_SERVER_PATH + listPath.get(l).split("pic")[1]);
						}
					}else{
						ossUrl.add(oss_url);	
					}
					
					ShopsPhotosInfo file = new ShopsPhotosInfo();
					file.setShopsId(rs.getRentId());
					file.setOssUrl(oss_url);
					file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
					shopsPhotosInfoDao.create(file);
				}
				
			}
			if (ossUrl!=null && ossUrl.size()>0) {
				rs.setThumbnailUrl(ossUrl.get(0));
			}
		}
		if(delIds!=null && delIds.size()>0){
			shopsPhotosInfoDao.batchUpdatePhotos(delIds);
		}
		int result = rentShopsDao.updateRentShopInformationRelease(rs);
		
		if (true) {
			resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		} else {
			resultDto.setCode(MessageDictionary.RESP_ERROR_CODE);
			resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		}
		return resultDto;
	}
	public HeadMessageDto editReleaseRentShopsByUserWeixin(RentShops rs,List<String> imgDatas,List<String> delIds) {
		HeadMessageDto resultDto=new HeadMessageDto();
		
		if (StringUtils.isEmpty(rs.getRentId()) || StringUtils.isEmpty(rs.getRentId())) {
			throw new IllegalArgumentException("求租编号不存在");
		} else if (imgDatas != null) {
			List<String> listPath = new ArrayList<String>();
			Map<String,String> downloadParams=new HashMap<String,String>();
			String access_token=GlobalConstantsPropertiesUtil.getString("access_token");
			downloadParams.put("access_token", access_token);
			if (imgDatas != null && imgDatas.size()!=0) {
				for (int i = 0; i < imgDatas.size(); i++) {
					downloadParams.put("media_id", imgDatas.get(i));
					String serverPath=HttpDownload.download(Constants.WEIXIN_DOWNLOAD_URL, downloadParams);
					listPath.add(serverPath);
				}
				
			}
			List<String> ossUrl = new ArrayList<String>();
			if(listPath!=null){
				OSSClientUtil oss = new OSSClientUtil();
				for (int l = 0; l < listPath.size(); l++) {
					String name = oss.uploadImg2Oss(listPath.get(l));
					String oss_url = oss.getImgUrl(name);
					//如过上传OSS文件找不到，则返回服务器路径
					////mnt/iccspace/upload/pic/2016/11/18/9/1479434108845.jpeg
					//http://www.iccspace.cn/upload/2016/11/18/9/1479434108845.jpeg
					if(oss_url==null){
						if (listPath.get(l).contains("pic")) {
							ossUrl.add(Constants.UPLOAD_SERVER_PATH + listPath.get(l).split("pic")[1]);
						}
					}else{
						ossUrl.add(oss_url);	
					}
					
					ShopsPhotosInfo file = new ShopsPhotosInfo();
					file.setShopsId(rs.getRentId());
					file.setOssUrl(oss_url);
					file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
					shopsPhotosInfoDao.create(file);
				}
				
			}
			if (ossUrl!=null && ossUrl.size()>0) {
				rs.setThumbnailUrl(ossUrl.get(0));
			}
		}
		if(delIds!=null && delIds.size()>0){
			shopsPhotosInfoDao.batchUpdatePhotos(delIds);
		}
		int result = rentShopsDao.updateRentShopInformationRelease(rs);
		
		if (result==Constants.AFFECTED_ROWS_1) {
			resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		} else {
			resultDto.setCode(MessageDictionary.RESP_ERROR_CODE);
			resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		}
		return resultDto;
	}
	@Override
	public PageInfo<Map<Object, Object>> getRentShopsListBySearch(Map<String, Object> m, Integer pageNum) {
		pageNum=pageNum==null?Constants.pageNum:pageNum;
		Integer pageSize=Constants.pageSize;

		PageHelper.startPage(pageNum, pageSize);
		List<Map<Object,Object>> list=rentShopsDao.queryRentShopsListBySearch(m);
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>(list);
		return page;
	}

}
