package com.icc.baidu_data_webapi.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.icc.baidu_data_webapi.model.hotel.Hotel;
import com.icc.baidu_data_webapi.model.hotel.JsonResult;

public class RestTemplateMain {

	private static final String URL = "http://api.map.baidu.com/place/v2/search";

	public static void listJSON(RestTemplate rt) {
		HttpEntity<String> entity = prepareGet(MediaType.APPLICATION_JSON);

		try {
			String url=URL+"?q="+URLEncoder.encode("饭店","utf-8")+"&region="+URLEncoder.encode("北京","utf-8")+"&output=json&ak=O6RW0LGNNync3xcSU4N8bG3ctdfax9q5";
			ResponseEntity<JsonResult> response = rt.exchange(url, HttpMethod.GET, entity, JsonResult.class);
			JsonResult re = response.getBody();
			List<Hotel> l = (List<Hotel>) re.getResults();
			for (Hotel e : l) {
				System.out.println("===3>" + e.getAddress() + ": " + e.getName());
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

	}

	private static HttpEntity<String> prepareGet(MediaType type) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<String> entity = new HttpEntity(headers);
		return entity;
	}
	private static RestTemplate getTemplate() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:spring.xml");		
		RestTemplate template = (RestTemplate) ctx.getBean("restTemplate");
		return template;
	}
	public static void main(String[] args) {
		RestTemplate restTemplate = getTemplate();
		listJSON(restTemplate);
	}
}
