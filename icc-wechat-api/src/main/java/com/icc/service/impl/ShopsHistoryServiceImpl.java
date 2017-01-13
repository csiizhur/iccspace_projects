package com.icc.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.opensearch.CloudsearchSearch;
import com.icc.aliyun.opensearch.OpensearchUtil;
import com.icc.aliyun.oss.OSSClientUtil;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.Location;
import com.icc.dao.ShopsHistoryDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.entity.ShopsHistory;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.ShopsHistoryService;
import com.icc.util.DateUtil;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpDownload;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;
import com.icc.wechat.exception.ParametersException;

@Service
public class ShopsHistoryServiceImpl implements ShopsHistoryService {

	//应用名称
    public static final String  INDEXNAME = "opensearch_iccspace";
    
    private static final String UPLOAD_SERVER_PATH="http://www.iccspace.cn/upload/";
	private Logger log=LoggerFactory.getLogger(getClass());
	@Autowired
	private ShopsHistoryDao shopsHistoryDao;
	@Autowired
	private ShopsPhotosInfoDao shopsPhotosInfoDao;
	
	@SuppressWarnings("unused")
	@Override
	public HeadMessageDto editReleaseShopsByUser(ShopsHistory sh, String shopsId, String baseShopsId,List<String> delIds, List<String> addDatas,Map<String,Object>shopsParams) {
		HeadMessageDto resultDto=new HeadMessageDto();
		
		if(StringUtils.isEmpty(shopsId)){
			throw new IllegalArgumentException("商铺编号不存在");
		}else{
			log.info("base64"+addDatas);
			List<String> listPath = new ArrayList<String>();
			if (addDatas != null && addDatas.size()!=0) {
				for (int i = 0; i < addDatas.size(); i++) {
					String base64Data = addDatas.get(i);
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
					if(oss_url==null){
						if(listPath.get(l).contains("pic")){
							ossUrl.add(UPLOAD_SERVER_PATH+listPath.get(l).split("pic")[1]);							
						}
					}else{
						ossUrl.add(oss_url);	
					}
					
					ShopsPhotosInfo file = new ShopsPhotosInfo();
					file.setShopsId(sh.getId());
					file.setOssUrl(oss_url);
					file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
					shopsPhotosInfoDao.create(file);
				}
				
			}
			if (ossUrl!=null && ossUrl.size()>0) {
				sh.setThumbnailURL(ossUrl.get(0));
			}
		
			if(delIds!=null && delIds.size()!=0){
				shopsPhotosInfoDao.batchUpdatePhotos(delIds);
			}
			if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE || sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT) {
				if (StringUtils.isNotEmpty(sh.getRentTrem()) && sh.getRentTrem().contains("年")
						&& sh.getRentTrem().contains("月")) {
					String[] str1 = sh.getRentTrem().split("年");
					String[] str2 = str1[1].split("月");
					BigDecimal count = BigDecimal.valueOf(Long.parseLong(str1[0]) * 12 + (Integer.parseInt(str2[0])));
					BigDecimal total = null;
					if (sh.getReleaseType() == Constants.RELEASE_SHOPS_TYPE_LEASE) {
						total = count.multiply(sh.getRentFee());
					}
					//求租总租金暂时忽略
					sh.setTotalRentFee(total);
				} else {
					throw new ParametersException("租期格式不正确");
				} 
			}
			//转租--原合同截止日
			String endDate=sh.getContractEndDate();
			if(StringUtils.isNotEmpty(endDate)){
				sh.setContractEndDate(DateUtil.dateToTimestamp(DateUtil.stringToDate2(endDate))+"");
			}
			int result=shopsHistoryDao.updateHistoryShopInformationRelease(sh);
			//暂不更新主表@Param("shopsId") String shopsId,@Param("baseShopsId") String baseShopsId,@Param("address")String address,@Param("shopSize")Double shopSize,@Param("lat")String lat,@Param("lng")String lng,
			shopsHistoryDao.updateBaseRentShopHistoryId(shopsParams);
			
			if (true) {
				resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			} else {
				resultDto.setCode(MessageDictionary.RESP_ERROR_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			}
		}
		return resultDto;
	}
	public HeadMessageDto editReleaseShopsByUserWeixin(ShopsHistory sh, String shopsId, String baseShopsId,List<String> delIds, List<String> addDatas,Map<String,Object>shopsParams) {
		HeadMessageDto resultDto=new HeadMessageDto();
		
		if(StringUtils.isEmpty(shopsId)){
			throw new IllegalArgumentException("商铺编号不存在");
		}else{
			List<String> listPath = new ArrayList<String>();
			Map<String,String> downloadParams=new HashMap<String,String>();
			String access_token=GlobalConstantsPropertiesUtil.getString("access_token");
			downloadParams.put("access_token", access_token);
			if (addDatas != null && addDatas.size()!=0) {
				for (int i = 0; i < addDatas.size(); i++) {
					downloadParams.put("media_id", addDatas.get(i));
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
					if(oss_url==null){
						if(listPath.get(l).contains("pic")){
							ossUrl.add(UPLOAD_SERVER_PATH+listPath.get(l).split("pic")[1]);							
						}
					}else{
						ossUrl.add(oss_url);	
					}
					
					ShopsPhotosInfo file = new ShopsPhotosInfo();
					file.setShopsId(sh.getId());
					file.setOssUrl(oss_url);
					file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
					shopsPhotosInfoDao.create(file);
				}
				
			}
			if (ossUrl!=null && ossUrl.size()>0) {
				sh.setThumbnailURL(ossUrl.get(0));
			}
			
			if(delIds!=null && delIds.size()!=0){
				shopsPhotosInfoDao.batchUpdatePhotos(delIds);
			}
			if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE || sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT) {
				if (StringUtils.isNotEmpty(sh.getRentTrem()) && sh.getRentTrem().contains("年")
						&& sh.getRentTrem().contains("月")) {
					String[] str1 = sh.getRentTrem().split("年");
					String[] str2 = str1[1].split("月");
					BigDecimal count = BigDecimal.valueOf(Long.parseLong(str1[0]) * 12 + (Integer.parseInt(str2[0])));
					BigDecimal total = null;
					if (sh.getReleaseType() == Constants.RELEASE_SHOPS_TYPE_LEASE) {
						total = count.multiply(sh.getRentFee());
					}
					//求租总租金暂时忽略
					//sh.setTotalRentFee(total);
					if(shopsParams.get("shopSize")!=null){
						sh.setTotalRentFee(sh.getRentFee().multiply(BigDecimal.valueOf((double)shopsParams.get("shopSize"))).add(sh.getEstateFee()));
					}
				} else {
					throw new ParametersException("租期格式不正确");
				} 
			}
			//转租--原合同截止日
			String endDate=sh.getContractEndDate();
			if(StringUtils.isNotEmpty(endDate)){
				sh.setContractEndDate(DateUtil.dateToTimestamp(DateUtil.stringToDate2(endDate))+"");
			}
			int result=shopsHistoryDao.updateHistoryShopInformationRelease(sh);
			//暂不更新主表@Param("shopsId") String shopsId,@Param("baseShopsId") String baseShopsId,@Param("address")String address,@Param("shopSize")Double shopSize,@Param("lat")String lat,@Param("lng")String lng,
			shopsHistoryDao.updateBaseRentShopHistoryId(shopsParams);
			
			if (true) {
				resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			} else {
				resultDto.setCode(MessageDictionary.RESP_ERROR_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			}
		}
		return resultDto;
	}

	public List<Map<String,Object>> queryShopsLocationList2(String keyWord,Location left, Location right) {
		List<Map<String,Object>> l=shopsHistoryDao.queryShopsLocationList(keyWord,left.getLat(), left.getLng(), right.getLat(), right.getLng());
		
		return l;
	}
	/**
	 * 使用opensearch
	 * @param keyWord
	 * @param left
	 * @param right
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Override
	public String queryShopsLocationList(Integer pageNo,String keyWord,Location left, Location right) {
		List<Map<String,Object>> l=shopsHistoryDao.queryShopsLocationList(keyWord,left.getLat(), left.getLng(), right.getLat(), right.getLng());
		String filter=left.getLat()+"<lat<"+right.getLat()+"AND"+left.getLng()+"<lng<"+right.getLng();
		try {
			/*CloudsearchSearch search = new CloudsearchSearch(OpensearchUtil.getInstance(filter));
			search.setQueryString("default:'园区'");
			search.setFormat("json");
			String result = search.search();*/
			CloudsearchSearch search = new CloudsearchSearch(OpensearchUtil.getInstance());
			search.addIndex(INDEXNAME);
			if (StringUtils.isNotEmpty(keyWord)) {
				//default:'园区'&&filter=lat=31.379310607910156
				//search.setQueryString("default:'园区'");
				search.setQueryString("default:'" + keyWord + "'");
			}
			String leftLat=left.getLat();
			String leftLng=left.getLng();
			String rightLat=right.getLat();
			String rightLng=right.getLng();
			if (StringUtils.isNotEmpty(leftLat) && StringUtils.isNotEmpty(leftLng)&&StringUtils.isNotEmpty(rightLat)&&StringUtils.isNotEmpty(rightLng)) {
				search.addFilter("&&filter=lat<=" + rightLat + " AND lat>=" + leftLat + " AND lng<=" + rightLng
						+ " AND lng>=" + leftLng);
			}
			//search.addFilter("&&filter=lat=31.379310607910156");
			//search.addFilter(right.getLat()+">lat>"+left.getLat());
			search.setFormat("json");
			if (pageNo!=null) {
				search.setStartHit((pageNo - 1) * Constants.pageSize > 0 ? (pageNo - 1) * Constants.pageSize : 0);
			}
			search.setHits(Constants.pageSize);
			String result = search.search();
			log.info("opensearch结果集-->"+result);
			return result;
		} catch (Exception e) {
			log.error("opensearch error:"+e);
			return null;
		}
	}

}
