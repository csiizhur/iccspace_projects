package com.icc.wechat.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.icc.dao.TextMessageDao;
import com.icc.util.MessageUtil;
import com.icc.wechat.message.response.Article;
import com.icc.wechat.message.response.NewsMessage;
import com.icc.wechat.message.response.TextMessage;

/**
 * ClassName: MsgDispatcher
 * @Description: 消息业务处理分发器
 * @author zhur
 * @date 2016年3月7日 下午4:04:21
 */
@Component
public class MsgDispatcher {
	
	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private  TextMessageDao textMessageDao;
	
	public  String processMessage(Map<String, String> map) {
		String openid=map.get("FromUserName"); //用户openid
		String mpid=map.get("ToUserName");   //公众号原始ID
		
		//普通文本消息
		TextMessage txtmsg=new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			String content=map.get("Content");
			Map<String,Object> keywordmap=new HashMap<String,Object>();
			keywordmap.put("keyWord", content);
			keywordmap.put("deleted", 0);
			logger.info(keywordmap.toString());
			String textContent=textMessageDao.queryKeyWordForReply(keywordmap);
			if(textContent!=null && !("").equals(textContent)){			
				txtmsg.setContent(textContent);
				logger.info(txtmsg.getContent());
				return MessageUtil.textMessageToXml(txtmsg);
			}else{
				logger.info(txtmsg.getContent());
				return null;
			}
		}
		
		//对图文消息
		NewsMessage newmsg=new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			System.out.println("==============这是图片消息！");
			Article article=new Article();
			article.setDescription("这是图文消息1"); //图文消息的描述
			article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); //图文消息图片地址
			article.setTitle("图文消息1");  //图文消息标题
			article.setUrl("http://www.cuiyongzhi.com");  //图文url链接
			List<Article> list=new ArrayList<Article>();
			list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
		}
		
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			txtmsg.setContent("");
			return MessageUtil.textMessageToXml(txtmsg);
		}
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
			return "";
		}
		
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
			System.out.println("==============这是视频消息！");
			return "";
		}
		
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！");
			return "";
		}

		return null;
	}
	
	public static void test(){
		String a=null;
		
		if(a!=null && !("").equals(a)){
			System.err.println("===");
		}else{
			System.err.println("---");
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}
