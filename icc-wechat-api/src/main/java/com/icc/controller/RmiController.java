package com.icc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icc.common.MessageDictionary;
import com.icc.common.model.HeadMessageDto;
import com.icc.common.model.JsonResult;
import com.icc.common.model.JsonResultSupport;
import com.icc.common.model.Location;
import com.icc.common.model.baidu.BaiduJsonResult;
import com.icc.common.model.baidu.BaiduJsonResult2;
import com.icc.common.model.baidu.Building;
import com.icc.common.model.baidu.ResidenceModel;
import com.icc.service.PlaceService;
import com.icc.util.HttpInvoker;
/**
 * 非RMI调用--百度api专用
 * @description
 * @author zhurun
 * @date 2016年11月4日下午4:20:29
 */
@Controller
@RequestMapping("/place")
public class RmiController {

	private static final String AK="aijWoikMIw9I08aHlx5bmS2AecG5gImK";
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(method=RequestMethod.GET,value="/queryPlace")
	@ResponseBody
	public JSON queryAllData(Integer pageSize,Integer pageNum, Double lng,Double lat,String q) throws Exception{
		String url="http://localhost:8895/baidu-data-api/api/place/getPoiByLocation?pageSize="+pageSize+"&pageNum="+pageNum+"&lng="+lng+"&lat="+lat+"&q="+q;
		URL getUrl = new URL(url);
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
        // 服务器
        connection.connect();
        
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        System.out.println("=============================");
        System.out.println("Contents of get request");
        System.out.println("=============================");
        String lines;
        StringBuilder sb=new StringBuilder();
        while ((lines = reader.readLine()) != null) {
            System.out.println(lines);
            sb.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        System.out.println("=============================");
        System.out.println("Contents of get request ends");
        System.out.println("=============================");
		
		return (JSON) JSON.parse(sb.toString());
	}
	
	/**
	 * 商户详情，根据商铺的位置，搜索周围2000米内的相关配套，传shopid
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method=RequestMethod.GET,value="/shopsPlaceDetail")
	public @ResponseBody String getShopsPlaceResidenceDetail(Location location) throws Exception{
		HeadMessageDto dto=new HeadMessageDto();
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		//List<Map<String,Object>> residence=new ArrayList<Map<String,Object>>();
		HashSet<ResidenceModel> residence = new HashSet<ResidenceModel>();
		List<Map<String,Object>> officeBuild=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> parkBuild=new ArrayList<Map<String,Object>>();
		
		List<Map<String,Object>> placeList=new ArrayList<Map<String,Object>>();
		String arg1=URLEncoder.encode("小区", "UTF-8");
		String arg2=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("industry_type:cater|sort_name:distance|sort_rule:1", "utf-8");
		int pageNum=0;
		int pageNum2=0;
		int pageNum3=0;
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum), arg1,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		
		String arg21=URLEncoder.encode("写字楼", "UTF-8");
		String arg22=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg23=URLEncoder.encode("2000", "utf-8");
		String arg24=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url2=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum2), arg21,arg22,arg23,arg24,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder result2=HttpInvoker.readContentFromGet(url2);
		
		String arg31=URLEncoder.encode("景点$公园", "UTF-8");
		String arg32=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg33=URLEncoder.encode("2000", "utf-8");
		String arg34=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url3=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum3), arg31,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder result3=HttpInvoker.readContentFromGet(url3);
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		BaiduJsonResult baidu_residence=objectMapper.readValue(result.toString(), BaiduJsonResult.class);
		int total=baidu_residence.getTotal();
		int pageSize=20;
		if (total!=0) {
			int totalPage = total / pageSize;
			for(int i=0;i<totalPage;i++){
				url=url.substring(0, url.lastIndexOf("=")+1)+(++pageNum);
				JSONObject bjr=JSONObject.parseObject(HttpInvoker.readContentFromGet(url).toString());
				List listRe=(List) bjr.get("results");
				baidu_residence.getResults().addAll(listRe);
			}
		}
		
		BaiduJsonResult baidu_office=objectMapper.readValue(result2.toString(), BaiduJsonResult.class);
		total=baidu_office.getTotal();
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				url2=url2.substring(0, url2.lastIndexOf("=")+1)+(++pageNum2);
				JSONObject bjr=JSONObject.parseObject(HttpInvoker.readContentFromGet(url2).toString());
				List listRe=(List<?>) bjr.get("results");
				baidu_office.getResults().addAll(listRe);
			}
		}
		
		BaiduJsonResult baidu_park=objectMapper.readValue(result3.toString(), BaiduJsonResult.class);
		total=baidu_park.getTotal();
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				url3=url3.substring(0, url3.lastIndexOf("=")+1)+(++pageNum3);
				JSONObject bjr=JSONObject.parseObject(HttpInvoker.readContentFromGet(url3).toString());
				List listRe=(List<?>) bjr.get("results");
				baidu_park.getResults().addAll(listRe);
			}
		}
		
		//java.util.LinkedHashMap cannot be cast to com.icc.common.model.baidu.ResidenceModel
		//com.alibaba.fastjson.JSONObject cannot be cast to java.util.LinkedHashMap
		//com.alibaba.fastjson.JSONObject cannot be cast to java.util.HashMap
		for(int res=0;res<baidu_residence.getResults().size();res++){
			Map<String, Object> hm=(Map<String, Object>) baidu_residence.getResults().get(res);
			ResidenceModel map=new ResidenceModel() ;
			String name= (String) hm.get("name");
			Pattern p = Pattern.compile("[\\d]");
	    	Matcher matcher = p.matcher(name);
	    	String resultName = matcher.replaceAll("-");
	    	if (resultName.contains("-")) {
				resultName = resultName.split("-")[0];
			}
			map.setName(resultName);
	    	map.setLocation(hm.get("location"));
	    	residence.add(map);
		}
		Map<String,Object> placeMap1=new HashMap<String,Object>();
		placeMap1.put("name", "住宅小区");
		placeMap1.put("list", residence);
		placeList.add(placeMap1);
		for(int res=0;res<baidu_office.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Map<String,Object> map=(Map<String, Object>) baidu_office.getResults().get(res);
			m.put("name", map.get("name"));
			m.put("location", map.get("location"));
			officeBuild.add(m);
		}
		Map<String,Object> placeMap2=new HashMap<String,Object>();
		placeMap2.put("name", "写字楼");
		placeMap2.put("list", officeBuild);
		placeList.add(placeMap2);
		for(int res=0;res<baidu_park.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Map<String,Object> map=(Map<String, Object>) baidu_park.getResults().get(res);
			m.put("name", map.get("name"));
			m.put("location", map.get("location"));
			parkBuild.add(m);
		}
		Map<String,Object> placeMap3=new HashMap<String,Object>();
		placeMap3.put("name", "景点公园");
		placeMap3.put("list", parkBuild);
		placeList.add(placeMap3);
		if(placeList.size()>0){
			dto.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			dto.setMessage(MessageDictionary.RESP_QRY_MSG);
		}else{
			dto.setCode(MessageDictionary.RESP_BAIDU_NODATA_CODE);
			dto.setMessage(MessageDictionary.RESP_BAIDU_NODATA_MSG);
		}
		jr.setHead(dto);
		jr.setBody(placeList);
		return objectMapper.writeValueAsString(jr);
	}
	/**
	 * 周边配套
	 * 交通，医疗，教育，政府，银行
	 * @param location
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/shopsPlaceAssortDetail")
	public @ResponseBody String getShopsPlaceAssortDetail(Location location) throws Exception{
		
		List<Map<String,Object>> trafficList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> healthList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> educationList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> govList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> bankList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> buildList=new ArrayList<Map<String,Object>>();
		
		int pageNum=0;
		int pageNum2=0;
		int pageNum3=0;
		int pageNum4=0;
		int pageNum5=0;
		int pageSize=20;
		ObjectMapper objectMapper=new ObjectMapper();
		
		//String arg31=URLEncoder.encode("交通$医疗$教育$政府$银行", "UTF-8");
		//交通
		String arg32=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg33=URLEncoder.encode("500", "utf-8");
		String arg34=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String traffic=URLEncoder.encode("交通", "UTF-8");
		String traffic_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum), traffic,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder result=HttpInvoker.readContentFromGet(traffic_url);
		BaiduJsonResult2 baidu_traffic=objectMapper.readValue(result.toString(), BaiduJsonResult2.class);
		int total=baidu_traffic.getTotal();	
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				traffic_url=traffic_url.substring(0, traffic_url.lastIndexOf("=")+1)+(++pageNum);
				BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(traffic_url).toString(), BaiduJsonResult2.class);
				List<Building> listRe=bjr.getResults();
				baidu_traffic.getResults().addAll(listRe);
			}
		}
		
		for(int res=0;res<baidu_traffic.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Building map=baidu_traffic.getResults().get(res);
			m.put("name", map.getName());
			m.put("location", map.getLocation());
			trafficList.add(m);
		}
		Map<String,Object> assortMap1=new HashMap<String,Object>();
		assortMap1.put("name", "交通");
		assortMap1.put("list", trafficList);
		buildList.add(assortMap1);
		//医疗
		String health=URLEncoder.encode("医疗", "UTF-8");
		String health_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum2), health,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder healthResult=HttpInvoker.readContentFromGet(health_url);
		BaiduJsonResult2 baidu_health=objectMapper.readValue(healthResult.toString(), BaiduJsonResult2.class);
		total=baidu_health.getTotal();	
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				health_url=health_url.substring(0, health_url.lastIndexOf("=")+1)+(++pageNum2);
				BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(health_url).toString(), BaiduJsonResult2.class);
				List<Building> listRe= bjr.getResults();
				baidu_health.getResults().addAll(listRe);
			}
		}
		
		for(int res=0;res<baidu_health.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Building map= baidu_health.getResults().get(res);
			m.put("name", map.getName());
			m.put("location", map.getLocation());
			healthList.add(m);
		}
		Map<String,Object> assortMap2=new HashMap<String,Object>();
		assortMap2.put("name", "医疗");
		assortMap2.put("list", healthList);
		buildList.add(assortMap2);
		//教育
		String education=URLEncoder.encode("教育", "UTF-8");
		String education_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum3), education,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder educationResult=HttpInvoker.readContentFromGet(education_url);
		BaiduJsonResult2 baidu_education=objectMapper.readValue(educationResult.toString(), BaiduJsonResult2.class);
		total=baidu_education.getTotal();	
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				education_url=education_url.substring(0, education_url.lastIndexOf("=")+1)+(++pageNum3);
				BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(education_url).toString(), BaiduJsonResult2.class);
				List<Building> listRe= bjr.getResults();
				baidu_education.getResults().addAll(listRe);
			}
		}
		
		for(int res=0;res<baidu_education.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Building map= baidu_education.getResults().get(res);
			m.put("name", map.getName());
			m.put("location", map.getLocation());
			educationList.add(m);
		}
		Map<String,Object> assortMap3=new HashMap<String,Object>();
		assortMap3.put("name", "教育");
		assortMap3.put("list", educationList);
		buildList.add(assortMap3);
		//银行
		String bank=URLEncoder.encode("银行", "UTF-8");
		String bank_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum4), bank,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder bankResult=HttpInvoker.readContentFromGet(bank_url);
		BaiduJsonResult2 baidu_bank=objectMapper.readValue(bankResult.toString(), BaiduJsonResult2.class);
		total=baidu_bank.getTotal();	
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				bank_url=bank_url.substring(0, bank_url.lastIndexOf("=")+1)+(++pageNum4);
				BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(bank_url).toString(), BaiduJsonResult2.class);
				List<Building> listRe= bjr.getResults();
				baidu_bank.getResults().addAll(listRe);
			}
		}
		
		for(int res=0;res<baidu_bank.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Building map= baidu_bank.getResults().get(res);
			m.put("name", map.getName());
			m.put("location", map.getLocation());
			bankList.add(m);
		}
		Map<String,Object> assortMap4=new HashMap<String,Object>();
		assortMap4.put("name", "银行");
		assortMap4.put("list", bankList);
		buildList.add(assortMap4);
		//政府
		String gov=URLEncoder.encode("政府", "UTF-8");
		String gov_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum5), gov,arg32,arg33,arg34,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
		StringBuilder govResult=HttpInvoker.readContentFromGet(gov_url);
		BaiduJsonResult2 baidu_gov=objectMapper.readValue(govResult.toString(), BaiduJsonResult2.class);
		total=baidu_gov.getTotal();	
		if(total!=0){
			for(int i=0;i<total/pageSize;i++){
				gov_url=gov_url.substring(0, gov_url.lastIndexOf("=")+1)+(++pageNum5);
				BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(gov_url).toString(), BaiduJsonResult2.class);
				List<Building> listRe= bjr.getResults();
				baidu_gov.getResults().addAll(listRe);
			}
		}
		
		for(int res=0;res<baidu_gov.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Building map= baidu_gov.getResults().get(res);
			m.put("name", map.getName());
			m.put("location", map.getLocation());
			govList.add(m);
		}
		Map<String,Object> assortMap5=new HashMap<String,Object>();
		assortMap5.put("name", "政府");
		assortMap5.put("list", govList);
		buildList.add(assortMap5);
		HeadMessageDto head=new HeadMessageDto();
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<HeadMessageDto>();
		if(buildList.size()>0){
			head.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			head.setMessage(MessageDictionary.RESP_QRY_MSG);
		}else{
			head.setCode(MessageDictionary.RESP_BAIDU_NODATA_CODE);
			head.setMessage(MessageDictionary.RESP_BAIDU_NODATA_MSG);
		}
		jr.setHead(head);
		jr.setBody(buildList);
		return objectMapper.writeValueAsString(jr);
	}
	/**
	 * 周边竞争
	 * 餐饮，美发，百货超市，酒店，娱乐等，没有发布全匹配，发布根据用户信息匹配
	 * @param location
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/shopsPlaceCompeteDetail")
	public @ResponseBody JsonResult<HeadMessageDto> getShopsPlaceCompeteDetail(Location location,String rentUserId) throws Exception{
		HeadMessageDto head=new HeadMessageDto();
		JsonResult<HeadMessageDto> jr=new JsonResultSupport<>();
		List<Map<String,Object>> caterList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> salonList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> supermarketList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> hotelList=new ArrayList<Map<String,Object>>();
		
		List<Map<String,Object>> userExpectListMap=new ArrayList<Map<String,Object>>();
		
		List<Map<String,String>> businessType=placeService.queryUserExpectBusiness(rentUserId);
		ObjectMapper objectMapper=new ObjectMapper();
		
		int pageNum=0;		
		int pageNum2=0;		
		int pageNum3=0;		
		int pageNum4=0;
		int pageSize=20;
		String arg2=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		if(businessType.size()>0){
			for(int i=0;i<businessType.size();i++){
				Map<String,Object> businessMap=new HashMap<String,Object>();
				List<Map<String,Object>> userExpectList=new ArrayList<Map<String,Object>>();
				
				Map<String,String> t=businessType.get(i);
				if (StringUtils.isNotEmpty(t.get("businessType"))) {
					businessMap.put("name", t.get("businessType"));
					/*if(i==0){
						type.append(t.get("businessType"));
					}else{
						type.append("$" + t.get("businessType"));						
					}*/
					String userEx=URLEncoder.encode(t.get("businessType"), "UTF-8");
					String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum), userEx,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
					StringBuilder result=HttpInvoker.readContentFromGet(url);
					BaiduJsonResult2 user_expectData=objectMapper.readValue(result.toString(), BaiduJsonResult2.class);
					int total=user_expectData.getTotal();
					if(total!=0){
						for(int j=0;j<total/pageSize;j++){
							url=url.substring(0, url.lastIndexOf("=")+1)+(++pageNum);
							BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(url).toString(), BaiduJsonResult2.class);
							List<Building> listRe= bjr.getResults();
							user_expectData.getResults().addAll(listRe);
						}
					}
					for(int res=0;res<user_expectData.getResults().size();res++){
						Map<String,Object> m=new HashMap<String,Object>();
						Building map=user_expectData.getResults().get(res);
						m.put("name", map.getName());
						m.put("location", map.getLocation());
						userExpectList.add(m);
					}
					businessMap.put("list", userExpectList);
					
					userExpectListMap.add(businessMap);
				}
			}
		}else{
			//餐饮
			String cater=URLEncoder.encode("餐饮$美食", "UTF-8");
			String cater_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum), cater,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
			StringBuilder result=HttpInvoker.readContentFromGet(cater_url);
			BaiduJsonResult2 baidu_cater=objectMapper.readValue(result.toString(), BaiduJsonResult2.class);
			int total=baidu_cater.getTotal();
			if(total!=0){
				for(int i=0;i<total/pageSize;i++){
					cater_url=cater_url.substring(0, cater_url.lastIndexOf("=")+1)+(++pageNum);
					BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(cater_url).toString(), BaiduJsonResult2.class);
					List<Building> listRe=bjr.getResults();
					baidu_cater.getResults().addAll(listRe);
				}
			}	
			for(int res=0;res<baidu_cater.getResults().size();res++){
				Map<String,Object> m=new HashMap<String,Object>();
				Building map= baidu_cater.getResults().get(res);
				m.put("name", map.getName());
				m.put("location", map.getLocation());
				caterList.add(m);
			}
			Map<String,Object> competeMap1=new HashMap<String,Object>();
			competeMap1.put("name", "餐饮");
			competeMap1.put("list", caterList);
			userExpectListMap.add(competeMap1);
			//美发
			String salon=URLEncoder.encode("美发", "UTF-8");
			String salon_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum2), salon,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
			StringBuilder salonResult=HttpInvoker.readContentFromGet(salon_url);
			BaiduJsonResult2 baidu_salon=objectMapper.readValue(salonResult.toString(), BaiduJsonResult2.class);
			total=baidu_salon.getTotal();
			if(total!=0){
				for(int i=0;i<total/pageSize;i++){
					salon_url=salon_url.substring(0, salon_url.lastIndexOf("=")+1)+(++pageNum2);
					BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(salon_url).toString(), BaiduJsonResult2.class);
					List<Building> listRe=bjr.getResults();
					baidu_salon.getResults().addAll(listRe);
				}
			}	
			for(int res=0;res<baidu_salon.getResults().size();res++){
				Map<String,Object> m=new HashMap<String,Object>();
				Building map= baidu_salon.getResults().get(res);
				m.put("name", map.getName());
				m.put("location", map.getLocation());
				salonList.add(m);
			}
			Map<String,Object> competeMap2=new HashMap<String,Object>();
			competeMap2.put("name", "美容美发");
			competeMap2.put("list", salonList);
			userExpectListMap.add(competeMap2);
			//超市
			String supermarket=URLEncoder.encode("百货超市", "UTF-8");
			String supermarket_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum3), supermarket,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
			StringBuilder supermarketResult=HttpInvoker.readContentFromGet(supermarket_url);
			BaiduJsonResult2 baidu_supermarket=objectMapper.readValue(supermarketResult.toString(), BaiduJsonResult2.class);
			total=baidu_supermarket.getTotal();
			if(total!=0){
				for(int i=0;i<total/pageSize;i++){
					supermarket_url=supermarket_url.substring(0, supermarket_url.lastIndexOf("=")+1)+(++pageNum3);
					BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(supermarket_url).toString(), BaiduJsonResult2.class);
					List<Building> listRe=bjr.getResults();
					baidu_supermarket.getResults().addAll(listRe);
				}
			}	
			for(int res=0;res<baidu_supermarket.getResults().size();res++){
				Map<String,Object> m=new HashMap<String,Object>();
				Building map= baidu_supermarket.getResults().get(res);
				m.put("name", map.getName());
				m.put("location", map.getLocation());
				supermarketList.add(m);
			}
			Map<String,Object> competeMap3=new HashMap<String,Object>();
			competeMap3.put("name", "百货超市");
			competeMap3.put("list", supermarketList);
			userExpectListMap.add(competeMap3);
			//酒店
			String hotel=URLEncoder.encode("酒店", "UTF-8");
			String hotel_url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size=20&page_num="+(pageNum4), hotel,arg2,arg3,arg4,"O6RW0LGNNync3xcSU4N8bG3ctdfax9q5");
			StringBuilder hotelResult=HttpInvoker.readContentFromGet(hotel_url);
			BaiduJsonResult2 baidu_hotel=objectMapper.readValue(hotelResult.toString(), BaiduJsonResult2.class);
			total=baidu_hotel.getTotal();
			if(total!=0){
				for(int i=0;i<total/pageSize;i++){
					hotel_url=hotel_url.substring(0, hotel_url.lastIndexOf("=")+1)+(++pageNum4);
					BaiduJsonResult2 bjr=objectMapper.readValue(HttpInvoker.readContentFromGet(hotel_url).toString(), BaiduJsonResult2.class);
					List<Building> listRe=bjr.getResults();
					baidu_hotel.getResults().addAll(listRe);
				}
			}	
			for(int res=0;res<baidu_hotel.getResults().size();res++){
				Map<String,Object> m=new HashMap<String,Object>();
				Building map= baidu_hotel.getResults().get(res);
				m.put("name", map.getName());
				m.put("location", map.getLocation());
				hotelList.add(m);
			}
			Map<String,Object> competeMap4=new HashMap<String,Object>();
			competeMap4.put("name", "酒店");
			competeMap4.put("list", hotelList);
			userExpectListMap.add(competeMap4);
		}
		if(userExpectListMap.size()>0){
			head.setCode(MessageDictionary.RESP_SUCCESS_CODE);
			head.setMessage(MessageDictionary.RESP_QRY_MSG);
		}else{
			head.setCode(MessageDictionary.RESP_BAIDU_NODATA_CODE);
			head.setMessage(MessageDictionary.RESP_BAIDU_NODATA_MSG);
		}
		jr.setHead(head);
		jr.setBody(userExpectListMap);
		return jr;
	}
}
