package com.icc.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.icc.common.Constants;
import com.icc.common.MessageDictionary;
import com.icc.common.WechatDictionary;
import com.icc.dto.UserShopsDto;
import com.icc.entity.BaseEntity;
import com.icc.entity.User;
import com.icc.service.OSSService;
import com.icc.service.UserService;
import com.icc.util.DateUtil;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpDownload;
import com.icc.util.PictureUtil;
import com.icc.util.PropertiesUtil;
import com.icc.util.StrUtil;
import com.icc.wechat.common.WechatConstants;
import com.icc.wechat.exception.ParametersException;

@Controller
@RequestMapping("/user")
public class UserController {

	protected final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private OSSService oSSService;
	@RequestMapping(method=RequestMethod.GET,value="/getUser",produces="application/json;charset=UTF-8")
	//produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
	public @ResponseBody User getUserInfo3(User user){
		//params：指定request中必须包含某些参数值是，才让该方法处理。
		String uuid=userService.queryUUID();
		logger.error(uuid+"==========================");
		userService.insertUsersInfo(user);
		User u=userService.getUserInfo(user);
		Map m=new HashMap<String,String>();
		
		return u;
	}
	@RequestMapping(method=RequestMethod.GET,value="/getUser")
	public @ResponseBody User getUserInfo1(User user){
		
		String uuid=userService.queryUUID();
		logger.error(uuid+"==========================");
		userService.insertUsersInfo(user);
		User u=userService.getUserInfo(user);
		logger.error(JSONObject.toJSONString(u));
		return u;
		
		/**
		 * <user>
			<age>0</age>
			<delete>0</delete>
			<id>6aee948e757011e6be9a40f2e937fddf</id>
			<isSubscribe>0</isSubscribe>
			<openId>zhur</openId>
			<sex>0</sex>
			<userType>0</userType>
			</user>
		 */
	}
	//consumes：指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	@RequestMapping(method=RequestMethod.POST,value="/getUser")
	public User getUserInfo2(User user){
		
		String uuid=userService.queryUUID();
		logger.error(uuid+"==========================");
		userService.insertUsersInfo(user);
		return userService.getUserInfo(user);
	}
	
	
	/**
	 * 获取用户信息
	 * produces="text/html;charset=UTF-8" 解决中文乱码
	 * @param userId
	 * @return
	 */
	//The resource identified by this request is only capable of generating responses with characteristics 406
	//@RequestMapping(method=RequestMethod.GET,value="/getUserInfo/{userId}",produces="text/html;charset=UTF-8")
	@RequestMapping(method=RequestMethod.GET,value="/getUserInfo/{userId}",produces="application/json;charset=UTF-8")
	public @ResponseBody  String getUserInfo(@PathVariable String userId){
		User user=userService.getUserInfoByUserId(userId);
		BaseEntity be=new BaseEntity();
		if(user!=null || ("").equals(user)){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			be.setBody(user);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
		}
		return JSONObject.toJSONString(be,SerializerFeature.WriteMapNullValue);
	}
	/**
	 * 新增关注用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/insertUserInfo")
	public @ResponseBody String insertUserInfo(User user){
		BaseEntity be=new BaseEntity();
		user.setLastAccessTime(DateUtil.dateToTimestamp(new Date()));
		int result=userService.insertUsersInfo(user);
		if(result==1){
			be.getHead().put(Constants.CODE, "0000");
			be.getHead().put(Constants.MESSAGE, "ok");
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_ERROR_MSG);
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 修改用户图像
	 * @param userId
	 * @param file
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/updateUserHeadImage")
	public @ResponseBody Map<String,Object> updateUserHeadImage(String userId,MultipartFile file){
		Map<String,Object> result = null;
		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("用户身份不存在，无法修改");
		} else {

			if (!file.isEmpty()) {

				String path = "D:/data/headimg/";
				File f = new File(path + file.getOriginalFilename());
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), f);
				} catch (Exception e) {
					e.printStackTrace();
				}

				User user = oSSService.uploadHeadImage(path + file.getOriginalFilename(),userId);

				result = userService.updateUserHeadImage(user);
			}
			
		}
		return result;
	}

	/**
	 * 更新用户信息
	 * data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAAAAAAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgs
	 * LEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUF
	 * BQUFBT/wAARCADkANYDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRI
	 * hMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJ
	 * @param user
	 * @param file
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateUserDetailsInfoByBase64")
	public @ResponseBody String updateUserInformations(User user) {
		BaseEntity be=new BaseEntity();
		String userId = user.getUserId();
		String headImage=user.getHeadImg();
		String ossHeadImage=null;
		if(!StringUtils.isEmpty(headImage)){
			
			//获取文件格式
	        String postfix = headImage.split("/")[1].split(";")[0];
	        //获取图片的Base64码
	        String str = headImage.split(",")[1];
	        Base64 decoder = new Base64();
	        try {
	            // Base64解码
	            byte[] bytes = decoder.decode(str);
	            for (int i = 0; i < bytes.length; ++i) {
	                // 调整异常数据
	                if (bytes[i] < 0) {
	                    bytes[i] += 256;
	                }
	            }
	            long title = Calendar.getInstance().getTimeInMillis();
	            //获取系统路径并设定文件保存的目录
	            String dir = PropertiesUtil.getString("headimage.path");//图片的上传路径，我这里是从工程的配置文件获取的
	            String fileName = title + "." + postfix;
	            // 生成jpeg图片
	            FileUtils.writeByteArrayToFile(new File(dir, fileName), bytes);
	            
	            ossHeadImage = oSSService.uploadUserHeadImage(dir + fileName,user.getUserId());
	            user.setHeadImg(ossHeadImage);
	        }catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			user.setHeadImg(null);//原微信图像
		}
		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("用户身份不存在，无法修改");
		} else {
			boolean result = userService.updateUserDetailsInformations(user);
			if (result) {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
			} else {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
			}
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateUserDetailsInfo")
	public @ResponseBody String updateUserInformationsByWeiXin(User user) {
		BaseEntity be=new BaseEntity();
		String userId = user.getUserId();
		String headImage=user.getHeadImg();
		String ossHeadImage=null;
		if(!StringUtils.isEmpty(headImage)){
			
			long p=Calendar.getInstance().getTimeInMillis();
			String path=PictureUtil.generateFolderPathByTime(p)+p+".";
			Map<String,String> downloadParams=new HashMap<String,String>();
			String access_token=GlobalConstantsPropertiesUtil.getString(WechatConstants.access_token);
			downloadParams.put(WechatConstants.access_token, access_token);
			downloadParams.put("media_id", headImage);
			String serverPath=HttpDownload.download(WechatConstants.get_pic_url,path, downloadParams);
	        ossHeadImage = oSSService.uploadUserHeadImage(serverPath,user.getUserId());
	        user.setHeadImg(ossHeadImage);
		}else{
			user.setHeadImg(null);//原微信图像
		}
		if (StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("用户身份不存在，无法修改");
		} else {
			boolean result = userService.updateUserDetailsInformations(user);
			if (result) {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
			} else {
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
			}
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 用户身份切换
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/updateIdentityInfoSwitch")
	public @ResponseBody String putIdentityInfoSwitch(@RequestParam String userId,@RequestParam int userRole){
		BaseEntity be=new BaseEntity();
		User u=new User();
		u.setUserId(userId);
		u.setUserRole(userRole);
		int result=userService.updateUserIdentitySwitch(u);
		if(result==1){
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
			be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_UPDATE_MSG);
		}else{
			be.getHead().put(Constants.CODE, MessageDictionary.RESP_ERROR_CODE);
			be.getHead().put(Constants.MESSAGE, "user identity switch fail");
		}
		return JSONObject.toJSONString(be);
	}
	/**
	 * 租户详情(求租者)
	 * @param us
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getTenantUsersInfoById")
	@ResponseBody
	public String getTenantUsersInfoById(UserShopsDto us){
		BaseEntity be=new BaseEntity();
		if(StrUtil.isValidity(us.getRentUserId())&&StrUtil.isValidity(us.getShopsId())){
			Map<String,Object> detail=userService.getTenantUsersInfoById(us);
			if(detail!=null){
				be.setBody(detail);
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_SUCCESS_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_QRY_MSG);
			}else{
				be.getHead().put(Constants.CODE, MessageDictionary.RESP_NODATA_CODE);
				be.getHead().put(Constants.MESSAGE, MessageDictionary.RESP_DATAISNULL_MSG);
			}
		}else{
			throw new ParametersException(WechatDictionary.ERROR_CODE_N1, WechatDictionary.ERROR_CODE_N1_DESC);
			
		}
		return JSONObject.toJSONString(be,SerializerFeature.WriteMapNullValue);
	}
}
