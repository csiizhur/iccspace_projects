package com.icc.baidu_data_api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icc.baidu_data_api.model.JsonResult;
import com.icc.baidu_data_api.model.Location;
import com.icc.baidu_data_api.model.response.PlaceResponse;
import com.icc.baidu_data_api.util.HttpInvoker;

@RequestMapping("/place")
@Controller
public class PlaceApiController {

	private static final String KEY="O6RW0LGNNync3xcSU4N8bG3ctdfax9q5";
	
	public static String getHotelByQ(String url){
		String responseMsg = "";
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			httpClient.executeMethod(getMethod);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = getMethod.getResponseBodyAsStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			responseMsg = out.toString("UTF-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getPoiByLocation")
	public @ResponseBody JsonResult getPoiByLocation(Integer pageSize,Integer pageNum,Location l,String q) throws JsonParseException, JsonMappingException, IOException{
		pageSize=pageSize!=null?pageSize:10;
		pageNum=pageNum!=null?pageNum:0;
		String arg=URLEncoder.encode(q, "UTF-8");
		String arg2=URLEncoder.encode(l.getLat()+","+l.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,arg3,arg4,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		System.err.println(r.getResults());
		
		return r;
	}
	public static void main(String[] args) throws Exception {
		BigDecimal lat=new BigDecimal(31.35568111018402);
		BigDecimal lng=new BigDecimal(120.68639710491348);
		Location l=new Location(lat,lng);
		System.err.println(new PlaceApiController().getResidencePoiByLocation(15, 0, l));
		
	}
	/**
	 * 写字楼
	 * @param pageSize
	 * @param pageNum
	 * @param l
	 * @param q
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getOfficeBuildPoiByLocation")
	public @ResponseBody JsonResult getOfficeBuildPoiByLocation(Integer pageSize,Integer pageNum,Location l) throws JsonParseException, JsonMappingException, IOException{
		pageSize=pageSize!=null?pageSize:10;
		pageNum=pageNum!=null?pageNum:0;
		String arg=URLEncoder.encode("写字楼", "UTF-8");
		String arg2=URLEncoder.encode(l.getLat()+","+l.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,arg3,arg4,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		System.err.println(r.getResults());
		
		return r;
	}
	/**
	 * 小区
	 * @param pageSize
	 * @param pageNum
	 * @param l
	 * @param q
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getResidencePoiByLocation")
	public @ResponseBody JsonResult getResidencePoiByLocation(Integer pageSize,Integer pageNum,Location l) throws JsonParseException, JsonMappingException, IOException{
		pageSize=pageSize!=null?pageSize:10;
		pageNum=pageNum!=null?pageNum:0;
		String arg=URLEncoder.encode("小区", "UTF-8");
		String arg2=URLEncoder.encode(l.getLat()+","+l.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,arg3,arg4,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		System.err.println(r.getResults());
		
		return r;
	}
	/**
	 * 写字楼
	 * 39.915,116.404,39.975,116.414
	 * @param pageSize
	 * @param pageNum
	 * @param l1
	 * @param l2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getOfficeBuildPoiByBounds")
	public @ResponseBody JsonResult getOfficBuildPoiByBounds(Integer pageSize,Integer pageNum,
			@ModelAttribute("l1")Location l1,
			@ModelAttribute("l2")Location l2) throws Exception{
		pageSize=pageSize!=null?pageSize:10;
		pageNum=pageNum!=null?pageNum:0;
		String arg=URLEncoder.encode("写字楼", "UTF-8");
		String arg2=URLEncoder.encode(l1.getLat()+","+l1.getLng()+","+l2.getLat()+","+l2.getLng(), "UTF-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?query=%s&bounds=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		System.err.println(r.getResults());
		
		return r;
	}
	/**
	 * 小区
	 * @param pageSize
	 * @param pageNum
	 * @param l1
	 * @param l2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET,value="/getResidencePoiByBounds")
	public @ResponseBody JsonResult getResidencePoiByBounds(Integer pageSize,Integer pageNum,
			@ModelAttribute("l1")Location l1,
			@ModelAttribute("l2")Location l2) throws Exception{
		pageSize=pageSize!=null?pageSize:20;
		pageNum=pageNum!=null?pageNum:0;
		String arg=URLEncoder.encode("住宅小区$胡同", "UTF-8");
		String arg2=URLEncoder.encode(l1.getLat()+","+l1.getLng()+","+l2.getLat()+","+l2.getLng(), "UTF-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?query=%s&bounds=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		System.err.println(r.getResults());
		
		Integer totalPages=(r.getTotal()+pageSize-1)/pageSize;
		for(int i=0;i<totalPages;i++){
			pageNum++;
			String url_=String.format("http://api.map.baidu.com/place/v2/search?query=%s&bounds=%s&output=json&ak=%s&page_size="+pageSize+"&page_num="+pageNum, arg,arg2,KEY);
			StringBuilder result_=HttpInvoker.readContentFromGet(url_);
			JsonResult r_=objectMapper.readValue(result_.toString(), JsonResult.class);
			Collection l=r_.getResults();
			r.getResults().addAll(l);
		}
		System.err.println(r.getResults().size());
		return r;
	}
	
	@InitBinder("l1")  
	public void initLeftLocationBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("l1.");  
	} 

	@InitBinder("l2")  
	public void initRightLocationBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("l2.");  
	}
	/**
	 * 商户详情，根据商铺的位置，搜索周围2000米内的相关配套，传shopid
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method=RequestMethod.GET,value="/shopsPlaceDetail")
	public @ResponseBody String getShopsPlaceResidenceDetail(Location location) throws IOException{
		String arg1=URLEncoder.encode("小区", "UTF-8");
		String arg2=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg3=URLEncoder.encode("2000", "utf-8");
		String arg4=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s", arg1,arg2,arg3,arg4,KEY);
		StringBuilder result=HttpInvoker.readContentFromGet(url);
		
		String arg21=URLEncoder.encode("写字楼", "UTF-8");
		String arg22=URLEncoder.encode(location.getLat()+","+location.getLng(), "UTF-8");
		String arg23=URLEncoder.encode("2000", "utf-8");
		String arg24=URLEncoder.encode("sort_name:distance|sort_rule:1", "utf-8");
		String url2=String.format("http://api.map.baidu.com/place/v2/search?q=%s&scope=2&location=%s&radius=%s&filter=%s&output=json&ak=%s", arg21,arg22,arg23,arg24,KEY);
		StringBuilder result2=HttpInvoker.readContentFromGet(url2);
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonResult r=objectMapper.readValue(result.toString(), JsonResult.class);
		JsonResult r2=objectMapper.readValue(result2.toString(), JsonResult.class);
		System.err.println(r.getResults());
		System.err.println(r2.getResults());
		List<Map<String,Object>> residence=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> officeBuild=new ArrayList<Map<String,Object>>();
		for(int res=0;res<r.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Map<String,Object> map=(Map<String, Object>) r.getResults().get(res);
			m.put("name", map.get("name"));
			m.put("location", map.get("location"));
			residence.add(m);
		}
		for(int res=0;res<r2.getResults().size();res++){
			Map<String,Object> m=new HashMap<String,Object>();
			Map<String,Object> map=(Map<String, Object>) r2.getResults().get(res);
			m.put("name", map.get("name"));
			m.put("location", map.get("location"));
			officeBuild.add(m);
		}
		PlaceResponse pr=new PlaceResponse();
		pr.setOfficeBuildList(officeBuild);
		pr.setOfficeBuildCount(r.getResults().size());
		pr.setResidenceList(residence);
		pr.setOfficeBuildCount(r2.getResults().size());
		return objectMapper.writeValueAsString(pr);
	}
}
