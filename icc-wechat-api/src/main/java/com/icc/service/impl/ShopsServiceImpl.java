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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icc.aliyun.oss.OSSClientUtil;
import com.icc.common.Constants;
import com.icc.common.HashCodeFactory;
import com.icc.common.MessageDictionary;
import com.icc.common.message.CHECKMSG;
import com.icc.common.message.ResourceManager;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.list.FilterRequestDto;
import com.icc.dao.RentShopsDao;
import com.icc.dao.ShopsDao;
import com.icc.dao.ShopsPhotosInfoDao;
import com.icc.dao.UserCollectionDao;
import com.icc.dao.UserDao;
import com.icc.dto.UserExpectShopsDto;
import com.icc.entity.Shops;
import com.icc.entity.ShopsHistory;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.entity.User;
import com.icc.service.ShopsService;
import com.icc.util.DateUtil;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpDownload;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;
import com.icc.util.StrUtil;
import com.icc.wechat.common.WechatConstants;
import com.icc.wechat.exception.ParametersException;

@Service
public class ShopsServiceImpl implements ShopsService {

	private Logger log=LoggerFactory.getLogger(getClass());
	public static final ResourceManager MESSAGE = ResourceManager.getInstance("com.icc.common.message.message");
	public static final ResourceManager checkmsg = ResourceManager.getInstance("com.icc.common.message.checkmsg");
		
	@Autowired
	private ShopsDao shopsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private HashCodeFactory hashCodeFactory;
	@Autowired
	private ShopsPhotosInfoDao shopsPhotosInfoDao;
	@Autowired
	private RentShopsDao rentShopsDao;
	@Autowired
	private UserCollectionDao userCollectionDao;
	@Override
	public PageInfo<Map<Object, Object>> getNewShopsList(Integer pageNo, Integer pageSize) {

		pageNo=pageNo==null?Constants.pageNum:pageNo;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		
		List<Map<Object,Object>> list=shopsDao.queryNewShopsList();
		
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>(list);
		return page;
	}
	@Override
	public PageInfo<Map<Object, Object>> getNewShopsList(Integer pageNo, Integer pageSize,List<String> areaNos,List<String> estatesTypes,Double minSize,Double maxSize,BigDecimal minRentFee,BigDecimal maxRentFee) {

		pageNo=pageNo==null?Constants.pageNum:pageNo;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Map<Object,Object>> list=shopsDao.queryNewShopsList(areaNos,estatesTypes,minSize,maxSize,minRentFee,maxRentFee);
		
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>(list);
		return page;
	}
	@Override
	public PageInfo<Map<Object, Object>> getNewRentShopsList(Integer pageNo, List<String> areaNos,String minExpectRentFee,String maxExpectRentFee,String minExpectSize,String maxExpectSize,List<String> estatesType) {
		
		pageNo=pageNo==null?Constants.pageNum:pageNo;
		Integer pageSize=Constants.pageSize;
		PageHelper.startPage(pageNo, pageSize);
		
		List<Map<Object,Object>> list=rentShopsDao.queryNewRentShopsList(areaNos, minExpectRentFee, maxExpectRentFee, minExpectSize, maxExpectSize, estatesType);
		
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>(list);
		return page;
	}
	@Override
	public Map<String, Object> getRecommendShopsList(String userId,Integer pageNo, Integer pageSize) {
		Map map=new HashMap<String,Object>();
		pageNo=pageNo==null?Constants.pageNum:pageNo;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		UserExpectShopsDto dto=new UserExpectShopsDto();
		PageInfo<Map<Object, Object>> page=new PageInfo<Map<Object,Object>>();
		if(StrUtil.isValidity(userId)){
			User u=userDao.getUserInfoByUserId(userId);
			dto=userDao.queryUserExceptRentShops(u.getUserId());
			if (dto!=null) {
				dto.setUserId(u.getUserId());// case when 填充
				PageHelper.startPage(pageNo, pageSize);
				Page<Map<Object, Object>> list = shopsDao.queryRecommendShopsBySort(dto);
				page = new PageInfo<Map<Object, Object>>(list);
				// mysql查询无数据
				if (page.getSize() == 0) {
					PageHelper.startPage(pageNo, pageSize);
					list = shopsDao.queryRecommendShopsBySortNoBusiness(dto);
					page = new PageInfo<Map<Object, Object>>(list);
				}
				map.put("page", page);
			}else{
				map.put("data", "user not release data");
			}
			return map;
		}else{
			PageHelper.startPage(pageNo, pageSize);
			List<Map<Object,Object>> list=shopsDao.queryRecommendShopsList();
			PageInfo<Map<Object,Object>> page2=new PageInfo<Map<Object,Object>>(list);
			map.put("page", page2);
			return map;
		}
	}
	@Override
	public PageInfo<Map<Object, Object>> getRecommendRentShopsList(String userId,Integer pageNo) {
		pageNo=pageNo==null?Constants.pageNum:pageNo;
		int pageSize=Constants.pageSize;
		UserExpectShopsDto dto=new UserExpectShopsDto();
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>();
		if(StrUtil.isValidity(userId)){
			User u=userDao.getUserInfoByUserId(userId);
			dto=userDao.queryUserExceptShops(u.getUserId());
			if (dto!=null) {
				dto.setUserId(u.getUserId());// case when 填充
				PageHelper.startPage(pageNo, pageSize);
				Page<Map<Object,Object>> list=rentShopsDao.queryRecommendRentShopsBySort(dto);
				page=new PageInfo<Map<Object,Object>>(list);
				if(page.getSize()==0){
					PageHelper.startPage(pageNo, pageSize);
					list = rentShopsDao.queryRecommendRentShopsBySortNoBusiness(dto);
					page = new PageInfo<Map<Object, Object>>(list);
				}
			}
			return page;
		}else{
			PageHelper.startPage(pageNo, pageSize);
			List<Map<Object,Object>> list=rentShopsDao.queryRecommendRentShopsList();
			PageInfo<Map<Object,Object>> page2=new PageInfo<Map<Object,Object>>(list);
			return page2;
		}
	}
	@Override
	public PageInfo<Map<Object, Object>> getShopsListBySearch(Map m,Integer pageNum, Integer pageSize) {
		pageNum=pageNum==null?Constants.pageNum:pageNum;
		pageSize=pageSize==null?Constants.pageSize:pageSize;

		PageHelper.startPage(pageNum, pageSize);
		List<Map<Object,Object>> list=shopsDao.queryShopsListBySearch(m);
		PageInfo<Map<Object,Object>> page=new PageInfo<Map<Object,Object>>(list);
		return page;
	}
	@Override
	public Map<String,Object> queryBaseShopsInfoUnique(int addressUnique) {
		return shopsDao.queryBaseShopsInfoUnique(addressUnique);
	}
	@Override
	public int insertBaseShopInformationRelease(Shops s) {
		return shopsDao.insertBaseRentShopInformationRelease(s);
	}
	@Override
	public int insertHistoryShopInformationRelease(ShopsHistory sh) {
		return shopsDao.insertRentHistoryShopInformationRelease(sh);
	}
	@Override
	public int updateBaseShopInformationRelease(Map<String, Object> map) {
		return shopsDao.updateBaseRentShopInformationRelease(map);
	}
	@Override
	public PageInfo<Map<String, Object>> queryShopInformationByUserId(String userId,Integer pageNum,Integer pageSize) {
		
		pageNum=pageNum==null?Constants.pageNum:pageNum;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		
		//获取role
		User user=userDao.getUserInfoByUserId(userId);
		int userRole=user.getUserRole();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		PageHelper.startPage(pageNum, pageSize);
		if(Constants.USER_ROLE_RENT==userRole){
			list=rentShopsDao.queryRentShopInformationByUserId(userId);
		}else{
			list=shopsDao.queryShopInformationByUserId(userId);
		}
		PageInfo<Map<String,Object>> page=new PageInfo<Map<String,Object>>(list);
		return page;
	}
	@Override
	public HeadMessageDto deleteReleaseShopsByShopsIdForUser(String userId, String shopsId) {

		int result = 0;
		//获取role
		User user=userDao.getUserInfoByUserId(userId);
		int userRole=user.getUserRole();
		if(Constants.USER_ROLE_RENT==userRole){
			result=rentShopsDao.deleteReleaseRentShopsByShopsIdForUser(userId, shopsId);
		}else{
			result=shopsDao.deleteReleaseShopsByShopsIdForUser(userId, shopsId);
			
		}
		HeadMessageDto resultDto=new HeadMessageDto();
		if(result==1){
				resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		}else{
			resultDto.setCode(MessageDictionary.RESP_ERROR_CODE);
			resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
		}
		return resultDto;
	}
	@SuppressWarnings("null")
	@Override
	public Map<String, Object> queryShopsInfoById(String shopsId,String currentUserId) {
		Map<String,Object> shopsInfo = null;
		if (StringUtils.isNotEmpty(currentUserId)) {
			if (StringUtils.isNotEmpty(shopsId)) {
				shopsInfo = shopsDao.queryShopsInfoById(shopsId);
				if (shopsInfo != null) {
					List<ShopsPhotosInfo> photos = shopsPhotosInfoDao.queryShopsPhotosListByShopsId(shopsId);
					if (photos.size() > 0) {
						shopsInfo.put("imageUrlList", photos);
					} else {
						shopsInfo.put("imageUrlList", null);
					}
				}

				//throw new ParametersException(MESSAGE.getFormattedString("user_is_not_avilble", currentUserId));
			}
			if (StringUtils.isNotEmpty(currentUserId)) {
				//是否已收藏
				String ucId = userCollectionDao.queryUserIsCollectionShops(currentUserId, shopsId);
				if (shopsInfo != null) {
					shopsInfo.put("isCollection", ucId != null ? true : false);
				}
			} else {
				throw new ParametersException(MESSAGE.getFormattedString("args_is_null", currentUserId));
			} 
		}else{
			//分享后无userId进入
			if (StringUtils.isNotEmpty(shopsId)) {
				shopsInfo = shopsDao.queryShopsInfoById(shopsId);
				if (shopsInfo != null) {
					List<ShopsPhotosInfo> photos = shopsPhotosInfoDao.queryShopsPhotosListByShopsId(shopsId);
					if (photos.size() > 0) {
						shopsInfo.put("imageUrlList", photos);
					} else {
						shopsInfo.put("imageUrlList", null);
					}
				}
			}
		}
		return shopsInfo;
	}
	
	@Override
	public PageInfo<Map<String, Object>> getHotSearchShopsList(String userId,Integer pageNum,Integer pageSize,FilterRequestDto frd) {
		int[] releaseType = new int[6];
		pageNum=pageNum==null?Constants.pageNum:pageNum;
		pageSize=pageSize==null?Constants.pageSize:pageSize;
		
		List<String> areaNoList=frd.getAreaNos();
		List<String> estatesTypeList=frd.getEstatesTypes();
		double minSize=frd.getMinSize();
		double maxSize=frd.getMaxSize();
		BigDecimal minRentFee=frd.getMaxRentFee();
		BigDecimal maxRentFee=frd.getMaxRentFee();
		if(StrUtil.isValidity(userId)){
			User user=userDao.getUserInfoByUserId(userId);
			int userRole=user.getUserRole();
			if(userRole==Constants.USER_ROLE_LEASE){
				releaseType[0]=Constants.RELEASE_SHOPS_TYPE_LEASE;
				releaseType[1]=Constants.RELEASE_SHOPS_TYPE_3;
				releaseType[2]=Constants.RELEASE_SHOPS_TYPE_4;
			}else if(Constants.USER_ROLE_RENT==userRole){
				releaseType[0]=Constants.RELEASE_SHOPS_TYPE_LEASE;
				releaseType[1]=Constants.RELEASE_SHOPS_TYPE_3;
				releaseType[2]=Constants.RELEASE_SHOPS_TYPE_4;
			}
		}else{
			//releaseType=null;//[0,0,0,0,0,0]
			releaseType[0]=1;
			releaseType[1]=3;
			releaseType[2]=4;
			log.debug("release_type为空");
			//throw new IllegalArgumentException("用户编号不能为空");//<if test="array!=null and array!=''">
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list=shopsDao.queryHotSearchShopsList(releaseType,areaNoList,estatesTypeList,minSize,maxSize,minRentFee,maxRentFee);
		PageInfo<Map<String,Object>> page=new PageInfo<>(list);
		return page;
	}
	@Override
	public PageInfo<Map<String, Object>> getHotSearchRentShopsList(Integer pageNum,List<String> areaNos,String minExpectRentFee,String maxExpectRentFee,String minExpectSize,String maxExpectSize,List<String>estatesType) {
		int releaseType = Constants.RELEASE_SHOPS_TYPE_RENT;
		pageNum=pageNum==null?Constants.pageNum:pageNum;
		int pageSize=Constants.pageSize;
		
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list=rentShopsDao.queryHotSearchRentShopsList(releaseType,areaNos,minExpectRentFee,maxExpectRentFee,minExpectSize,maxExpectSize,estatesType);
		PageInfo<Map<String,Object>> page=new PageInfo<>(list);
		return page;
	}
	@Override
	public boolean releaseShopsInformationAddOrUpdate(Shops shop,ShopsHistory sh,String historyShopId,String baseShopId) {
		
		boolean result=true;
		
		//基础表地址hash值
		StringBuffer addressUnique=hashCodeFactory.getStrUnique(shop.getCityNo(),shop.getAreaNo(),shop.getStreetNo(),shop.getAddress());
		shop.setAddressUnique(addressUnique.toString());
		//String shopsAddress=shop.getCityNo()+shop.getAreaNo()+shop.getStreetNo()+shop.getAddress();
		Map<String,Object> map=new HashMap<String,Object>();
		
		//int addressUnique=shopsAddress.hashCode();

		String baseShopsInfo=shopsDao.queryBaseShopsInfoUnique(addressUnique.toString());
		if(StringUtils.isEmpty(baseShopsInfo)){
			shop.setId(baseShopId);
			shop.setHistoryId(historyShopId);
			shopsDao.insertBaseRentShopInformationRelease(shop);
			
			sh.setId(historyShopId);
			sh.setBaseShopsId(baseShopId);
			shopsDao.insertRentHistoryShopInformationRelease(sh);
			
			return result;
			
		}else{
			//baseshop存在  addressunique=原来的值   history=新的historyid  子表的baseShopsId=主表原来的id
			
			map.put("historyId", historyShopId);
			map.put("addressUnique", addressUnique);
			shopsDao.updateBaseRentShopInformationRelease(map);
			
			//在insert子表
			sh.setId(historyShopId);
			sh.setBaseShopsId(baseShopsInfo);
			shopsDao.insertRentHistoryShopInformationRelease(sh);
			
			return result;
		}
	}
	@SuppressWarnings({ "null", "unused" })
	@Override
	public HeadMessageDto releaseShopsInformation(Shops s, ShopsHistory sh, String historyShopId, String baseShopId,
			String rentShopId,List<String> data,String userId) {
		if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE || sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT) {
			if(sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE){
				if(sh.getRentFee()==null){
					throw new ParametersException("租金不能为空");	
				}
			}
			if(sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT){

				if(sh.getExpectRentFeeMin()==null||sh.getExpectRentFeeMax()==null){
					throw new ParametersException("期望租金不能为空");
				}
			}
			if (StringUtils.isNotEmpty(sh.getRentTrem()) && sh.getRentTrem().contains("年")
					&& sh.getRentTrem().contains("月") ) {
				String[] str1 = sh.getRentTrem().split("年");
				String[] str2 = str1[1].split("月");
				BigDecimal count = BigDecimal.valueOf(Long.parseLong(str1[0]) * 12 + (Integer.parseInt(str2[0])));
				BigDecimal total = null;
				if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE) {
					total = count.multiply(sh.getRentFee());
				}
				//求租总租金暂时忽略
				sh.setTotalRentFee(total);
			} else {
				throw new ParametersException(
						checkmsg.getFormattedString(CHECKMSG.VALIDATION_RENTTREM_IS_ERROR, sh.getRentTrem()));
			} 
		}
		HeadMessageDto resultDto = new HeadMessageDto();
		User user=userDao.getUserInfoByUserId(userId);
		if(user==null){
			throw new ParametersException("user is not null");
		}
		// base64上传至本地
		List<String> listPath = new ArrayList<String>();
		log.info("base64"+data);
		if (data != null && data.size()!=0) {
			log.info("图片上传开始");
			for (int i = 0; i < data.size(); i++) {
				String base64Data = data.get(i);
				// 获取文件格式
				String postfix = base64Data.split("/")[1].split(";")[0];
				// 获取图片的Base64码
				String str = base64Data.split(",")[1];
				Base64 decoder = new Base64();
				// Base64解码
				byte[] bytes = decoder.decode(str);
				for (int j = 0; j < bytes.length; ++j) {
					// 调整异常数据
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
				// 生成jpeg图片
				try {
					FileUtils.writeByteArrayToFile(new File(dirPath, fileName), bytes);
				} catch (IOException e) {
					log.error("upload fail");
					e.printStackTrace();
				}

				listPath.add(dirPath + fileName);
			}

		}
		// 本地上传至oss
		List<String> ossUrl = new ArrayList<String>();
		if(listPath!=null){
			OSSClientUtil oss = new OSSClientUtil();
			for (int l = 0; l < listPath.size(); l++) {
				String name = oss.uploadImg2Oss(listPath.get(l));
				String oss_url = oss.getImgUrl(name);
				//如过上传OSS文件找不到，则返回服务器路径
				if(oss_url==null){
					if (listPath.get(l).contains("pic")) {
						ossUrl.add(Constants.UPLOAD_SERVER_PATH + listPath.get(l).split("pic")[1]);
					}
				}else{
					ossUrl.add(oss_url);	
				}
				
				ShopsPhotosInfo file = new ShopsPhotosInfo();
				if(user.getUserRole()==Constants.USER_ROLE_LEASE){
					
					file.setShopsId(historyShopId);
				}else{
					file.setShopsId(rentShopId);
				}
				file.setOssUrl(oss_url);
				file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
				// insert shops_photos_info 删除了外键 防止CONSTRAINT `FK_Reference_6` FOREIGN KEY (`SHOPSID`) REFERENCES `SHOPS_HISTORY` (`ID`)
				shopsPhotosInfoDao.create(file);
			}
			
		}

		// insert shops_history
		// 设置thumbnail_url为oss第一张图
		if(ossUrl!=null && ossUrl.size()>0){
			sh.setThumbnailURL(ossUrl.get(0));			
		}
		if (StringUtils.isNotEmpty(userId)) {
			sh.setUserId(userId);
		}else{
			throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_USERID_ISNULL_ERROR, userId));
		}
		//判断类型
		int rentType=sh.getReleaseType();
		if(rentType==Constants.USER_ROLE_RENT){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("businessType", sh.getBusinessType());
			map.put("expectShopSize", sh.getExpectShopSize());
			map.put("expectShopSizeMin", sh.getExpectShopSizeMin());
			map.put("expectShopSizeMax", sh.getExpectShopSizeMax());
			map.put("rentTrem", sh.getRentTrem());
			map.put("estatesType", sh.getEstatesType());
			map.put("renovation", sh.getRenovation());
			map.put("parkingLot", sh.getParkingLot());
			map.put("subletBrand", sh.getSubletBrand());
			map.put("userId", sh.getUserId());
			map.put("releaseType", sh.getReleaseType());
			map.put("cityNo", s.getCityNo());
			map.put("areaNo", s.getAreaNo());
			map.put("address", s.getAddress());
			map.put("mobilePhone", sh.getMobilePhone());
			map.put("expectRentFeeMin", sh.getExpectRentFeeMin());
			map.put("expectRentFeeMax", sh.getExpectRentFeeMax());
			map.put("stories", sh.getStories());
			if (ossUrl!=null && ossUrl.size()>0) {
				map.put("thumbnailUrl", ossUrl.get(0));
			}else{
				log.info("图片未上传");
			}
			rentShopsDao.insertRentShopsInfo(map);
			resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			resultDto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}else
		if(rentType==1 || rentType==3 || rentType==4){
			//转租--原合同截止日--select str_to_date('2016年6月6日','%Y年%m月%d日')
			String endDate=sh.getContractEndDate();
			if(StringUtils.isNotEmpty(endDate)){
				sh.setContractEndDate(DateUtil.dateToTimestamp(DateUtil.stringToDate2(endDate))+"");
			}
			// 基础表地址hash值
			StringBuffer addressUnique = hashCodeFactory.getStrUnique(s.getCityNo(), s.getAreaNo(), s.getStreetNo(),
					s.getAddress());
			s.setAddressUnique(addressUnique.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			
			String baseShopsId = shopsDao.queryBaseShopsInfoUnique(addressUnique.toString());
			if (StringUtils.isEmpty(baseShopsId)) {
				s.setId(baseShopId);
				s.setHistoryId(historyShopId);
				shopsDao.insertBaseRentShopInformationRelease(s);
				
				sh.setId(historyShopId);
				sh.setBaseShopsId(baseShopId);
				shopsDao.insertRentHistoryShopInformationRelease(sh);
				
				resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				resultDto.setMessage(MessageDictionary.RESP_INSERT_MSG);
				
			} else {
				// baseshop存在 addressunique=原来的值 history=新的historyid
				// 子表的baseShopsId=主表原来的id
				
				/*map.put("historyId", historyShopId);
				map.put("addressUnique", addressUnique);//StringBuffer不能更新
				map.put("baseShopsId", baseShopsId);
				int resultBase=shopsDao.updateBaseRentShopInformationRelease(map);
				log.info("影响行数："+resultBase);*/
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("cityNo", s.getCityNo());
				params.put("areaNo", s.getAreaNo());
				params.put("address", s.getAddress());
				params.put("lat", s.getLat());
				params.put("lng", s.getLng());
				//@Param("historyId")String historyId,@Param("addressUnique")String addressUnique,@Param("baseShopsId")String baseShopsId,Map<String,String> params
				params.put("historyId", historyShopId);
				params.put("addressUnique", addressUnique.toString());
				params.put("baseShopsId", baseShopsId);
				int resultBase2=shopsDao.updateBaseRentShopInformationRelease(params);
				log.info("影响行数："+resultBase2);
				
				// 在insert子表
				sh.setId(historyShopId);
				sh.setBaseShopsId(baseShopsId);
				int result=shopsDao.insertRentHistoryShopInformationRelease(sh);
				log.error("影响行数"+result);
				resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
				resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
			}
			
		}else{
			throw new ParametersException(MESSAGE.getFormattedString("releaseType_is_error", rentType));
		}
		return resultDto;
	}
	public HeadMessageDto releaseShopsInformationByWeixin(Shops s, ShopsHistory sh, String historyShopId, String baseShopId,
			String rentShopId,List<String> data,String userId) {
		if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE || sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT) {
			if(sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE){
				if(sh.getRentFee()==null){
					//throw new ParametersException("租金不能为空");	
				}
			}
			if(sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_RENT){
				
				if(sh.getExpectRentFeeMin()==null||sh.getExpectRentFeeMax()==null){
					//throw new ParametersException("期望租金不能为空");
				}
			}
			if (StringUtils.isNotEmpty(sh.getRentTrem()) && sh.getRentTrem().contains("年")
					&& sh.getRentTrem().contains("月") ) {
				String[] str1 = sh.getRentTrem().split("年");
				String[] str2 = str1[1].split("月");
				//BigDecimal count = BigDecimal.valueOf(Long.parseLong(str1[0]) * 12 + (Integer.parseInt(str2[0])));
				if (sh.getReleaseType()==Constants.RELEASE_SHOPS_TYPE_LEASE) {
					//sh.getRentFee().compareTo(new BigDecimal(0));
					if (sh.getEstateFee()!=null && sh.getRentFee()!=null) {
						sh.setTotalRentFee(sh.getRentFee().multiply(BigDecimal.valueOf(s.getShopSize())).add(sh.getEstateFee()));
					}
				}
			} else {
				throw new ParametersException(
						checkmsg.getFormattedString(CHECKMSG.VALIDATION_RENTTREM_IS_ERROR, sh.getRentTrem()));
			} 
		}
		HeadMessageDto resultDto = new HeadMessageDto();
		User user=userDao.getUserInfoByUserId(userId);
		if(user==null){
			throw new ParametersException("user is not null");
		}
		// 微信下载至本地
		List<String> listPath = new ArrayList<String>();
		Map<String,String> downloadParams=new HashMap<String,String>();
		String access_token=GlobalConstantsPropertiesUtil.getString("access_token");
		downloadParams.put("access_token", access_token);
		log.info("downloadParams:"+downloadParams);
		if (data != null && data.size()!=0) {
			log.info("图片上传开始");
			for (int i = 0; i < data.size(); i++) {
				downloadParams.put("media_id", data.get(i));
				log.error(downloadParams+"==Map");
				String serverPath=HttpDownload.download(WechatConstants.get_pic_url, downloadParams);
				listPath.add(serverPath);
			}
			
		}
		// 本地上传至oss
		List<String> ossUrl = new ArrayList<String>();
		if(listPath!=null){
			OSSClientUtil oss = new OSSClientUtil();
			for (int l = 0; l < listPath.size(); l++) {
				String name = oss.uploadImg2Oss(listPath.get(l));
				String oss_url = oss.getImgUrl(name);
				//如过上传OSS文件找不到，则返回服务器路径
				if(oss_url==null){
					if (listPath.get(l).contains("pic")) {
						ossUrl.add(Constants.UPLOAD_SERVER_PATH + listPath.get(l).split("pic")[1]);
					}
				}else{
					ossUrl.add(oss_url);	
				}
				
				ShopsPhotosInfo file = new ShopsPhotosInfo();
				if(user.getUserRole()==Constants.USER_ROLE_LEASE){
					
					file.setShopsId(historyShopId);
				}else{
					file.setShopsId(rentShopId);
				}
				file.setOssUrl(oss_url);
				file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
				// insert shops_photos_info 删除了外键 防止CONSTRAINT `FK_Reference_6` FOREIGN KEY (`SHOPSID`) REFERENCES `SHOPS_HISTORY` (`ID`)
				shopsPhotosInfoDao.create(file);
			}
			
		}
		
		// insert shops_history
		// 设置thumbnail_url为oss第一张图
		if(ossUrl!=null && ossUrl.size()>0){
			sh.setThumbnailURL(ossUrl.get(0));			
		}
		if (StringUtils.isNotEmpty(userId)) {
			sh.setUserId(userId);
		}else{
			throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_USERID_ISNULL_ERROR, userId));
		}
		//判断类型
		int rentType=sh.getReleaseType();
		if(rentType==Constants.USER_ROLE_RENT){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("businessType", sh.getBusinessType());
			map.put("expectShopSize", sh.getExpectShopSize());
			map.put("expectShopSizeMin", sh.getExpectShopSizeMin());
			map.put("expectShopSizeMax", sh.getExpectShopSizeMax());
			map.put("rentTrem", sh.getRentTrem());
			map.put("estatesType", sh.getEstatesType());
			map.put("renovation", sh.getRenovation());
			map.put("parkingLot", sh.getParkingLot());
			map.put("subletBrand", sh.getSubletBrand());
			map.put("userId", sh.getUserId());
			map.put("releaseType", sh.getReleaseType());
			map.put("cityNo", s.getCityNo());
			map.put("areaNo", s.getAreaNo());
			map.put("address", s.getAddress());
			map.put("mobilePhone", sh.getMobilePhone());
			map.put("expectRentFeeMin", sh.getExpectRentFeeMin());
			map.put("expectRentFeeMax", sh.getExpectRentFeeMax());
			map.put("stories", sh.getStories());
			map.put("id", rentShopId);
			if (ossUrl!=null && ossUrl.size()>0) {
				map.put("thumbnailUrl", ossUrl.get(0));
			}else{
				log.info("图片未上传");
			}
			rentShopsDao.insertRentShopsInfoExistId(map);
			resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			resultDto.setMessage(MessageDictionary.RESP_INSERT_MSG);
		}else
			if(rentType==1 || rentType==3 || rentType==4){
				//转租--原合同截止日--select str_to_date('2016年6月6日','%Y年%m月%d日')
				String endDate=sh.getContractEndDate();
				if(StringUtils.isNotEmpty(endDate)){
					sh.setContractEndDate(DateUtil.dateToTimestamp(DateUtil.stringToDate2(endDate))+"");
				}
				// 基础表地址hash值
				StringBuffer addressUnique = hashCodeFactory.getStrUnique(s.getCityNo(), s.getAreaNo(), s.getStreetNo(),
						s.getAddress());
				s.setAddressUnique(addressUnique.toString());
				Map<String, Object> map = new HashMap<String, Object>();
				
				String baseShopsId = shopsDao.queryBaseShopsInfoUnique(addressUnique.toString());
				if (StringUtils.isEmpty(baseShopsId)) {
					s.setId(baseShopId);
					s.setHistoryId(historyShopId);
					shopsDao.insertBaseRentShopInformationRelease(s);
					
					sh.setId(historyShopId);
					sh.setBaseShopsId(baseShopId);
					shopsDao.insertRentHistoryShopInformationRelease(sh);
					
					resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
					resultDto.setMessage(MessageDictionary.RESP_INSERT_MSG);
					
				} else {
					// baseshop存在 addressunique=原来的值 history=新的historyid
					// 子表的baseShopsId=主表原来的id
					
					/*map.put("historyId", historyShopId);
				map.put("addressUnique", addressUnique);//StringBuffer不能更新
				map.put("baseShopsId", baseShopsId);
				int resultBase=shopsDao.updateBaseRentShopInformationRelease(map);
				log.info("影响行数："+resultBase);*/
					Map<String,Object> params=new HashMap<String,Object>();
					params.put("cityNo", s.getCityNo());
					params.put("areaNo", s.getAreaNo());
					params.put("address", s.getAddress());
					params.put("lat", s.getLat());
					params.put("lng", s.getLng());
					//@Param("historyId")String historyId,@Param("addressUnique")String addressUnique,@Param("baseShopsId")String baseShopsId,Map<String,String> params
					params.put("historyId", historyShopId);
					params.put("addressUnique", addressUnique.toString());
					params.put("baseShopsId", baseShopsId);
					int resultBase2=shopsDao.updateBaseRentShopInformationRelease(params);
					log.info("影响行数："+resultBase2);
					
					// 在insert子表
					sh.setId(historyShopId);
					sh.setBaseShopsId(baseShopsId);
					int result=shopsDao.insertRentHistoryShopInformationRelease(sh);
					log.error("影响行数"+result);
					resultDto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
					resultDto.setMessage(MessageDictionary.RESP_UPDATE_MSG);
				}
				
			}else{
				throw new ParametersException(MESSAGE.getFormattedString("releaseType_is_error", rentType));
			}
		return resultDto;
	}
	
	
	

}
