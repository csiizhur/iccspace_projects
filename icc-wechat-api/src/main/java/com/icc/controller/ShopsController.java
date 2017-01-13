package com.icc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.icc.common.Constants;
import com.icc.common.IdFactory;
import com.icc.common.MessageDictionary;
import com.icc.common.message.CHECKMSG;
import com.icc.common.message.ResourceManager;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.common.model.Location;
import com.icc.common.model.ReleaseShopsData;
import com.icc.common.model.UpdateShopsData;
import com.icc.common.model.list.FilterRequestDto;
import com.icc.entity.BaseEntity;
import com.icc.entity.RentShops;
import com.icc.entity.Shops;
import com.icc.entity.ShopsHistory;
import com.icc.entity.ShopsPhotosInfo;
import com.icc.service.ImageService;
import com.icc.service.OSSService;
import com.icc.service.RentShopsService;
import com.icc.service.ShopsHistoryService;
import com.icc.service.ShopsService;
import com.icc.service.UserService;
import com.icc.util.FilesUtil;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;
import com.icc.wechat.exception.ParametersException;

@Controller
@RequestMapping(value="/shops")
@SessionAttributes("openId")
public class ShopsController {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public static final ResourceManager checkmsg = ResourceManager.getInstance("com.icc.common.message.checkmsg");
	@Autowired
	private ShopsService shopsService;
	
	@Autowired 
	private IdFactory idFactory;
	
	@Autowired
	private UserService userService;
	
	@Resource
	private ImageService photoService;
	
	@Resource
	private OSSService ossService;
	
	@Autowired
	private ShopsHistoryService shopsHistoryService;
	
	@Autowired
	private RentShopsService rentShopsService;
	
	/**
	 * 新铺(出租)
	 * @param pageNo
	 * @param pageSize
	 * @param areaNos
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getNewShopsList")
	public @ResponseBody String getNewShopsList(Integer pageNum,Integer pageSize,
			//@RequestParam(required=false,value="areaNos")List<String> areaNos,
			String areaNos,
			//@RequestParam(required=false,value="estatesTypes")List<String> estatesTypes,
			String estatesTypes,
			@RequestParam(required=false)Double minSize,
			@RequestParam(required=false)Double maxSize,
			BigDecimal minRentFee,BigDecimal maxRentFee){
		List<String> areaNoList=new ArrayList<String>();
		List<String> estatesTypeList=new ArrayList<String>();
		if (StringUtils.isNotEmpty(areaNos)) {
			if (areaNos.contains(",")) {
				String[] areas = areaNos.split(",");
				for (int i = 0; i < areas.length; i++) {
					areaNoList.add(areas[i]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_AREANOS_IS_ERROR, areaNos));
			}
		}else{
			areaNoList=null;
		}
		if(StringUtils.isNotEmpty(estatesTypes)){
			if (estatesTypes.contains(",")) {
				String[] estatesTypess = estatesTypes.split(",");
				for (int j = 0; j < estatesTypess.length; j++) {
					estatesTypeList.add(estatesTypess[j]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_ESTATESTYPES_IS_ERROR, estatesTypes));
			}
		}else{
			estatesTypeList=null;
		}
		BaseEntity be=new BaseEntity();
		PageInfo<Map<Object,Object>> page=shopsService.getNewShopsList(pageNum, pageSize,areaNoList,estatesTypeList,minSize,maxSize,minRentFee,maxRentFee);
		
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 新铺(求租)
	 * @param pageNo
	 * @param areaNos
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getNewRentShopsList")
	public @ResponseBody String getNewRentShopsList(Integer pageNum,//@RequestParam(required=false,value="areaNos")List<String> areaNos,
			String areaNos,
			String minExpectRentFee,String maxExpectRentFee,
			String minExpectSize,String maxExpectSize,
			//@RequestParam(required=false,value="estatesType")List<String> estatesType
			String estatesTypes){
		BaseEntity be=new BaseEntity();
		List<String> areaNoList=new ArrayList<String>();
		List<String> estatesTypeList=new ArrayList<String>();
		if (StringUtils.isNotEmpty(areaNos)) {
			if (areaNos.contains(",")) {
				String[] areas = areaNos.split(",");
				for (int i = 0; i < areas.length; i++) {
					areaNoList.add(areas[i]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_AREANOS_IS_ERROR, areaNos));
			}
		}else{
			areaNoList=null;
		}
		if(StringUtils.isNotEmpty(estatesTypes)){
			if (estatesTypes.contains(",")) {
				String[] estatesTypess = estatesTypes.split(",");
				for (int j = 0; j < estatesTypess.length; j++) {
					estatesTypeList.add(estatesTypess[j]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_ESTATESTYPES_IS_ERROR, estatesTypes));
			}
		}else{
			estatesTypeList=null;
		}
		PageInfo<Map<Object,Object>> page=shopsService.getNewRentShopsList(pageNum,areaNoList,minExpectRentFee,maxExpectRentFee,minExpectSize,maxExpectSize,estatesTypeList);
		
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
		
	}
	/**
	 * 推荐(出租)
	 * @param pageNum
	 * @param pageSize
	 * @param openId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getRecommendShopsList")
	public @ResponseBody String getRecommendShopsList(Integer pageNum,Integer pageSize,String userId){
		BaseEntity be=new BaseEntity();
			Map<String,Object> map=shopsService.getRecommendShopsList(userId,pageNum, pageSize);
			String data=(String) map.get("data");
			PageInfo<Map<Object,Object>> page=(PageInfo<Map<Object, Object>>) map.get("page");
			if (StringUtils.isEmpty(data)) {
				if (page.getSize() > 0) {
					be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
					be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
					be.setBody(page);
					logger.info(page.toString());
				}else {
					be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
					be.getHead().put(Constants.MESSAGE, "no recommend shops");
				} 
			}else{
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
				be.getHead().put(Constants.MESSAGE, data);
			}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 推荐(求租)
	 * @param pageNum
	 * @param pageSize
	 * @param openId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getRecommendRentShopsList")
	public @ResponseBody String getRecommendRentShopsList(Integer pageNum,String userId){
		BaseEntity be=new BaseEntity();
		PageInfo<Map<Object,Object>> page=shopsService.getRecommendRentShopsList(userId,pageNum);
		
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		
		
		return JSONObject.toJSONString(be);
	}
	
	/**
	 * 热搜(出租)
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getHotSearchShopsList")
	@ResponseBody
	public String getHotSearchShopsList(Integer pageNum,Integer pageSize,String userId,
			String areaNos,
			String estatesTypes,
			@RequestParam(required=false)Double minSize,
			@RequestParam(required=false)Double maxSize,
			BigDecimal minRentFee,BigDecimal maxRentFee
			){
		
		FilterRequestDto dto=new FilterRequestDto();
		List<String> areaNoList=new ArrayList<String>();
		List<String> estatesTypeList=new ArrayList<String>();
		if (StringUtils.isNotEmpty(areaNos)) {
			if (areaNos.contains(",")) {
				String[] areas = areaNos.split(",");
				for (int i = 0; i < areas.length; i++) {
					areaNoList.add(areas[i]);
				} 
			} else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_AREANOS_IS_ERROR, areaNos));
			}
		}else{
			areaNoList=null;
		}
		if(StringUtils.isNotEmpty(estatesTypes)){
			if (estatesTypes.contains(",")) {
				String[] estatesTypess = estatesTypes.split(",");
				for (int j = 0; j < estatesTypess.length; j++) {
					estatesTypeList.add(estatesTypess[j]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_ESTATESTYPES_IS_ERROR, estatesTypes));
			}
		}else{
			estatesTypeList=null;
		}
		dto.setAreaNos(areaNoList);
		dto.setEstatesTypes(estatesTypeList);
		if (minSize!=null) {
			dto.setMinSize(minSize);
		}
		if(maxSize!=null){
			dto.setMaxSize(maxSize);	
		}
		dto.setMinRentFee(minRentFee);
		dto.setMaxRentFee(maxRentFee);
		BaseEntity be=new BaseEntity();
		PageInfo<Map<String,Object>> page=shopsService.getHotSearchShopsList(userId,pageNum, pageSize,dto);
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
		
	}
	/**
	 * 热搜(求租)
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getHotSearchRentShopsList")
	@ResponseBody
	public String getHotSearchRentShopsList(Integer pageNum,String userId,
			//@RequestParam(required=false)List<String> areaNos,
			String areaNos,
			String minExpectRentFee,String maxExpectRentFee,
			String minExpectSize,String maxExpectSize,
			//@RequestParam(required=false)List<String> estatesType
			String estatesTypes
			){
		BaseEntity be=new BaseEntity();
		List<String> areaNoList=new ArrayList<String>();
		List<String> estatesTypeList=new ArrayList<String>();
		if (StringUtils.isNotEmpty(areaNos)) {
			if (areaNos.contains(",")) {
				String[] areas = areaNos.split(",");
				for (int i = 0; i < areas.length; i++) {
					areaNoList.add(areas[i]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_AREANOS_IS_ERROR, areaNos));
			}
		}else{
			areaNoList=null;
		}
		if(StringUtils.isNotEmpty(estatesTypes)){
			if (estatesTypes.contains(",")) {
				String[] estatesTypess = estatesTypes.split(",");
				for (int j = 0; j < estatesTypess.length; j++) {
					estatesTypeList.add(estatesTypess[j]);
				} 
			}else{
				throw new ParametersException(checkmsg.getFormattedString(CHECKMSG.VALIDATION_ESTATESTYPES_IS_ERROR, estatesTypes));
			}
		}else{
			estatesTypeList=null;
		}
		PageInfo<Map<String,Object>> page=shopsService.getHotSearchRentShopsList(pageNum,areaNoList,minExpectRentFee,maxExpectRentFee,minExpectSize,maxExpectSize,estatesTypeList);
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
		
	}
	
	/**
	 * 搜索(出租)
	 * @param shop
	 * @param sh
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getShopsListBySearch")
	public @ResponseBody String getShopsListBySearch(Shops shop, ShopsHistory sh,Integer pageNum,Integer pageSize){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("shopsSize", shop.getShopSize());
		m.put("areaName", shop.getAreaName());
		m.put("shopsName", sh.getShopsName());
		m.put("streetName", shop.getStreetName());
		PageInfo<Map<Object,Object>> page=shopsService.getShopsListBySearch(m, pageNum, pageSize);
		BaseEntity be=new BaseEntity();
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 搜索(求租)
	 * @param shop
	 * @param sh
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getRentShopsListBySearch")
	public @ResponseBody String getRentShopsListBySearch(RentShops rs,Integer pageNum){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("expectShopsSizeMin", rs.getExpectShopSizeMin());
		m.put("expectShopsSizeMin", rs.getExpectShopSizeMax());
		m.put("address", rs.getAddress());
		m.put("expectRentFeeMin", rs.getExpectRentFeeMin());
		m.put("expectRentFeeMax", rs.getExpectRentFeeMax());
		PageInfo<Map<Object,Object>> page=rentShopsService.getRentShopsListBySearch(m, pageNum);
		BaseEntity be=new BaseEntity();
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
	}

	@RequestMapping(method=RequestMethod.POST,value="/rentShopInformationRelease")
	public @ResponseBody JsonResult<HeadMessageDto> rentShopInformationRelease(Shops s,ShopsHistory sh,String addDatas,String userId){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		String historyShopId=idFactory.getUUID();
		String baseShopId=idFactory.getUUID();
		String rentShopId=idFactory.getUUID();
		
		List<String> mediaId=(List<String>) JSONObject.parse(addDatas);
		if (mediaId!=null && !"[]".equals(mediaId)) {
			
			/*if (addDatas.contains("data:")) {
				String[] a = addDatas.split("data:");
				for (int i = 0; i < a.length; i++) {
					if (i != 0) {
						imgDatas.add(a[i]);
					}
				} 
			} else{
				throw new ParametersException("base64_vaild_error");
				
			}*/
			
		}
		logger.error("发布商铺");
		logger.error(mediaId+"==List");
		HeadMessageDto dto=shopsService.releaseShopsInformationByWeixin(s, sh, historyShopId, baseShopId, rentShopId,mediaId,userId);
		result.setHead(dto);
		return result;
	}
	/**
	 * 商铺发布
	 * @param shop
	 * @param sh
	 * @param session
	 * @param imgUrlList 图片List 非必输参数@RequestParam
	 * @param imgNameList 图片List 必输参数@RequestParam
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/rentShopInformationRelease2")
	public @ResponseBody String rentShopInformationRelease(Shops shop, ShopsHistory sh,HttpSession session,
			@RequestParam(required=false,value="imgUrlList []") String [] imgUrlList,@RequestParam(required=false)List<String> imgNameList){
		BaseEntity be=new BaseEntity();
		
		String historyShopId=idFactory.getUUID();
		String baseShopId=idFactory.getUUID();
		
		List<String> ossUrlList = new ArrayList<String>();

		if(imgNameList!=null){
			
			// 下面开始insert 图片 --应该放在service层由事物控制
			for (int i = 0; i < imgNameList.size(); i++) {
				
				ShopsPhotosInfo file = new ShopsPhotosInfo();
				file.setShopsId(historyShopId);
				// file.setImageURL1(imgUrlList.get(i));
				file.setImageName(imgNameList.get(i));
				// 获取旧文件路径
				String oldPath = PictureUtil.getImgUrl(imgNameList.get(i), "temp");
				if (oldPath.length() <= 0) {
					be.getHead().put(Constants.CODE, MessageDictionary.FILE_NOT_FIND_CODE);
					be.getHead().put(Constants.MESSAGE, MessageDictionary.FILE_NOT_FIND);
				} else {
					String newPath = PropertiesUtil.getString("image.local.path")
							+ oldPath.substring(oldPath.lastIndexOf("pic") + 3);
					FilesUtil.createFolder(newPath.substring(0, newPath.lastIndexOf("/")));
					// 移动文件到本地正式目录
					FilesUtil.moveFile(oldPath, newPath);
					// oss上传
					String ossurl = ossService.uploadImage(imgNameList.get(i));
					logger.info(ossurl);
					ossUrlList.add(ossurl);
					file.setOssUrl(ossurl);
					file.setOssUploadFlg(Constants.OSS_UPLOAD_FLG_1);
					photoService.create(file);
				}
			}
			// 设置thumbnail_url为oss第一张图
			sh.setThumbnailURL(ossUrlList.get(0));
		}else{
			logger.info("图片未上传");
		}
		boolean result = shopsService.releaseShopsInformationAddOrUpdate(shop, sh, historyShopId, baseShopId);
		if (result) {
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
			
		} else {
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_INSERT_MSG);
		}

		return JSONObject.toJSONString(be);
	}
	
	/**
	 * 编辑商铺(出租)
	 * @param sh
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/editReleaseShops")
	public @ResponseBody JsonResult<HeadMessageDto> updateRentShopInformationRelease(ShopsHistory sh,
			String address,Double shopSize,String lat,String lng,String cityNo,String areaNo,
			//@RequestParam(required=false) List<String> delIds,
			//@RequestParam(required=false) List<String> addDatas
			//String addDatas
			UpdateShopsData data
			){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		HeadMessageDto dto=new HeadMessageDto();
		Map<String,Object> params=new HashMap<String,Object>();
		if (StringUtils.isNotEmpty(sh.getId())) {
			//基础表数据需更新
			params.put("cityNo", cityNo);
			params.put("areaNo", areaNo);
			params.put("shopsId", sh.getId());
			params.put("baseShopsId", sh.getBaseShopsId());
			params.put("address", address);
			params.put("shopSize", shopSize);
			params.put("lat", lat);
			params.put("lng", lng);
			List<String> imgDatas=new ArrayList<String>();
			if (data.getAddDatas()!=null && !"[]".equals(data.getAddDatas())) {
				imgDatas.addAll((List<String>) JSONObject.parse(data.getAddDatas()));
			}
			List<String> delIds=new ArrayList<String>();
			if(data.getDelIds()!=null && !"[]".equals(data.getDelIds())){
				delIds.addAll((List<String>) JSONObject.parse(data.getDelIds()));
			}
				/*
				if (data.getDelIds()!=null) {
					for (int i = 0; i <= data.getDelIds().size(); i += 2) {
						if (i!=data.getDelIds().size()) {
							delIds.add(data.getDelIds().get(i) + data.getDelIds().get(i + 1));
						}
					} 
				}
				
				if (data.getAddDatas()!=null) {
					for (int i = 0; i <= data.getAddDatas().size(); i += 2) {
						if (i!=data.getAddDatas().size()) {
							addDatas.add(data.getAddDatas().get(i) + data.getAddDatas().get(i + 1));
						}
					} 
				}*/
			
			
			dto=shopsHistoryService.editReleaseShopsByUserWeixin(sh, sh.getId(), sh.getBaseShopsId(),delIds,imgDatas,params);
		}else{
			dto.setCode(MessageDictionary.RESP_ERROR_CODE);
			dto.setMessage("商铺编号不能为空");
		}
		result.setHead(dto);
		
		return result;
	}
	/**
	 * 编辑商铺(求租)
	 * @param rs
	 * @param imgData
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/editReleaseRentShops")
	public @ResponseBody JsonResult<HeadMessageDto> editRentShopInformationRelease(RentShops rs,
			//@RequestParam(required=false)List<String> addDatas,
			//@RequestParam(required=false)List<String> delIds
			UpdateShopsData data){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		List<String> addDatas=new ArrayList<String>();
		if (data.getAddDatas()!=null && data.getAddDatas().contains(",")) {
			addDatas.addAll((List<String>) JSONObject.parse(data.getAddDatas()));
		}
		List<String> delIds=new ArrayList<String>();
		if(data.getDelIds()!=null && !"[]".equals(data.getDelIds())){
			delIds.addAll((List<String>) JSONObject.parse(data.getDelIds()));
		}
		HeadMessageDto dto=rentShopsService.editReleaseRentShopsByUserWeixin(rs,addDatas,delIds);
		result.setHead(dto);
		
		return result;
	}
	
	/**
	 * 我的发布(出/求租)
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getUsersReleaseShops")
	public @ResponseBody String queryShopInformationByUserId(@RequestParam String userId,Integer pageNum,Integer pageSize){
		BaseEntity be=new BaseEntity();
		PageInfo<Map<String,Object>> page=shopsService.queryShopInformationByUserId(userId,pageNum,pageSize);
		if(page.getPageSize()>0){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(page);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	
	/**
	 * 删除我的发布(出/求租)
	 * @param userId
	 * @param shopsId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/deleteReleaseShopsForUser")
	public @ResponseBody JsonResult<HeadMessageDto> deleteShopsInfomationByShopsIdForUser(String userId,String shopsId){
		JsonResult<HeadMessageDto> result=new JsonResultSupport<HeadMessageDto>();
		HeadMessageDto dto=shopsService.deleteReleaseShopsByShopsIdForUser(userId, shopsId);
		result.setHead(dto);
		
		return result;
	}
	
	/**
	 * 商铺详情(出租)
	 * @param shopsId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getShopsInfoById")
	public @ResponseBody String getShopsInfoById(@RequestParam String shopsId,@RequestParam(required=false) String currentUserId){
		BaseEntity be=new BaseEntity();
		Map<String,Object> shopsInfo=shopsService.queryShopsInfoById(shopsId,currentUserId);
		if(shopsInfo!=null){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(shopsInfo);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
			
		}
		return JSONObject.toJSONString(be,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 商铺详情上下页
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getShopsInfoByIdPage")
	public @ResponseBody String getShopsInfoByIdPage(String listType,String userId,Integer pageNum,FilterRequestDto dto){
		String shopId=null;
		Map<String,Object> resu=new HashMap<String,Object>();
		if("new".equals(listType)){
			PageInfo<Map<Object,Object>> page=shopsService.getNewShopsList(pageNum, 1);
			Map<Object,Object> newShop=page.getList().get(0);
			shopId=(String) newShop.get("shopsId");
		}else if("hot".equals(listType)){
			PageInfo<Map<String,Object>> page=shopsService.getHotSearchShopsList(userId,pageNum, 1,dto);
			Map<String,Object> hotShop=page.getList().get(0);
			shopId=(String) hotShop.get("shopsId");
		}else if("recom".equals(listType)){
			//PageInfo<Map<Object,Object>> page=shopsService.getRecommendShopsList(userId,pageNum, 1);
			//Map<Object,Object> recoShop=page.getList().get(0);
			//shopId=(String) recoShop.get("shopsId");
		}else{
			resu.put("message", "listType is error");
		}
		resu.put("currentShopsId", shopId);
		return JSONObject.toJSONString(resu);
	}
	/**
	 * 出租商铺LocationList
	 * @param leftLocation
	 * @param rightLocation
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getShopsLocationList")
	public @ResponseBody String getShopsLocationListBySearch(
			@ModelAttribute("leftLocation")Location leftLocation,
			@ModelAttribute("rightLocation")Location rightLocation,
			String keyWord,Integer pageNo){
		BaseEntity be=new BaseEntity();
		logger.info("leftLocation-->"+leftLocation.getLat()+"leftLocation"+leftLocation.getLng());
		String dto=shopsHistoryService.queryShopsLocationList(pageNo,keyWord,leftLocation, rightLocation);
		
		if(StringUtils.isNotEmpty(dto)){
			be.getHead().put(Constants.CODE,MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE,MessageDictionary.RESP_QRY_MSG);
			be.setBody(dto);
		}else{
			be.getHead().put(Constants.CODE,MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE,MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be,SerializerFeature.WriteMapNullValue);
	}
	@InitBinder("leftLocation")  
	public void initLeftLocationBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("leftLocation.");  
	} 

	@InitBinder("rightLocation")  
	public void initRightLocationBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("rightLocation.");  
	}
	/**
	 * 求租商铺LocationList--没有地理位置
	 * @param leftLocation
	 * @param rightLocation
	 * @return
	 */
	/*@RequestMapping(method=RequestMethod.GET,value="/getRentShopsLocationList")
	public @ResponseBody String getRentShopsLocationListBySearch(
			@ModelAttribute("leftLocation")Location leftLocation,
			@ModelAttribute("rightLocation")Location rightLocation,
			String keyWord){
		BaseEntity be=new BaseEntity();
		List<Map<String,Object>> dto=shopsHistoryService.queryShopsLocationList(keyWord,leftLocation, rightLocation);
		be.setBody(dto);
		
		if(dto.size()>0){
			be.getHead().put(Constants.CODE,MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE,MessageDictionary.RESP_QRY_MSG);
		}else{
			be.getHead().put(Constants.CODE,MessageDictionary.RESP_NODATA_CODE);
			be.getHead().put(Constants.MESSAGE,MessageDictionary.RESP_DATAISNULL_MSG);
		}
		return JSONObject.toJSONString(be,SerializerFeature.WriteMapNullValue);
	}*/
}
