package com.icc.wechat.dispatcher.material;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icc.util.GlobalConstantsPropertiesUtil;
import com.icc.util.HttpUtils;

/**
 * 素材
 * @description
 * @author zhurun
 * @date 2016年12月13日下午3:51:09
 */
public class MaterialMain {

	private final String GET_MATERIAL="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
	private final String BATCHGET_MATERIAL="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
	/**
	 * 获取素材列表
	 * @param params
	 * @return
	 */
	public String getBatchMaterial(JSONObject params){		
		try {
			String result=HttpUtils.sendPostBuffer(BATCHGET_MATERIAL+GlobalConstantsPropertiesUtil.getString("access_token"), params.toJSONString());
			JSONObject jsonResult=JSONObject.parseObject(result);
			JSONArray item=(JSONArray) jsonResult.get("item");
			JSONObject media=(JSONObject)item.get(0);
			String mediaId=media.getString("media_id");
			JSONObject content=media.getJSONObject("content");
			JSONArray newsItems=content.getJSONArray("news_item");
			return mediaId;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取永久素材
	 * @param mediaId
	 * @return
	 */
	public List<JSONObject> getMaterial(String mediaId){
		JSONObject json=new JSONObject();
		json.put("media_id", "mediaId");
		try {
			String result=HttpUtils.sendPostBuffer(GET_MATERIAL+GlobalConstantsPropertiesUtil.getString("access_token"), json.toJSONString());
			JSONObject jsonResult=JSONObject.parseObject(result);
			List<JSONObject> listItem=(List<JSONObject>) jsonResult.get("news_item");

			return listItem;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
} 
