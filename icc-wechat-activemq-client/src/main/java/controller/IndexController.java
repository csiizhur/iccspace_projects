package controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.User;
import response.ResultRespone;

@Controller
@RequestMapping("/index")
public class IndexController {
	//return new String("你好".getBytes(), "UTF-8");
	private Logger log=LoggerFactory.getLogger(getClass());
	@RequestMapping(value="/test",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResultRespone index(){
		ResultRespone respone = new ResultRespone();
		respone.setData("欢迎来到我的界面");
		return respone;
	}
	//请求类型 json 返回类型 xml 中文不乱码
	//localhost:8081/icc-wechat-activemq-client/index/xml?name=朱润&age=26
	@RequestMapping(value="xml",method=RequestMethod.GET)
	@ResponseBody
	public User getXmlData(User u){
		return u;
		/*<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
		<user>
		<age>26</age>
		<name>朱润</name>
		</user>*/
	}
	//请求 类型 xml 返回 类型 json 中文乱码
	/*<?xml version="1.0" encoding="UTF-8"?>
	<user>
	  <name>朱润</name>
	  <age>23</age>
	</user>*/
	@RequestMapping(value="xml",method=RequestMethod.POST,consumes="application/xml;charset=UTF-8")
	@ResponseBody
	public String getXmlData2(@RequestBody User u){
		return u.toString();
		//User [name=??, age=23]
		//符串转换和对象转换用的是两个转换器，而String的转换器中固定了转换编码为"ISO-8859-1"
		//StringHttpMessageConverter重写一遍，将其中的编码改为UTF-8 后返回User [name=朱润, age=23]
	}
	//请求  和 返回 类型 均是 xml格式  中文不乱码
	/*<?xml version="1.0" encoding="UTF-8"?>
	<user>
	  <name>朱润</name>
	  <age>23</age>
	</user>*/
	@RequestMapping(value="2xml",method=RequestMethod.POST,produces="application/xml;charset=UTF-8")
	@ResponseBody
	public User getXmlData3(@RequestBody User u){
		return u;
		/*<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
		<user>
		<age>23</age>
		<name>朱润</name>
		</user>*/
	}
	/*{
		"data":["we","e","ew"]
		}*/
	//application/json
	@RequestMapping(value="array",method=RequestMethod.POST)
	@ResponseBody
	public String getArrayData3(@RequestBody DataList d){
		log.info("===");
		return d.toString();
	}
	//?data=fsda&data=sdfsdf
	@RequestMapping(value="array2",method=RequestMethod.POST)
	@ResponseBody
	public String getArrayData2(String[] d){
		
		return d.toString();
	}
	
}