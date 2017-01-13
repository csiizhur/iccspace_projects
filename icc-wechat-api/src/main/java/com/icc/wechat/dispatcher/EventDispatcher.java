package com.icc.wechat.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.icc.dao.UserDao;
import com.icc.entity.User;
import com.icc.util.DateUtil;
import com.icc.util.HttpPostUploadUtil;
import com.icc.util.MessageUtil;
import com.icc.wechat.exception.ParametersException;
import com.icc.wechat.message.response.Article;
import com.icc.wechat.message.response.Image;
import com.icc.wechat.message.response.ImageMessage;
import com.icc.wechat.message.response.NewsMessage;

/**
 * ClassName: EventDispatcher
 * 
 * @Description: 事件消息业务分发器
 * @author zhur
 * @date 2016年3月7日 下午4:04:41
 */
@Component
public class EventDispatcher {
	private static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

	@Autowired
	private WeixinUseInfo getUseInfo;
	@Autowired
	private UserDao openUserMapper;

	public String processEvent(Map<String, String> map) {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID
		
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);
		
		//对图文消息
		NewsMessage newmsg=new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
			try {
				//记录关注的用户信息
				Map<String,String> useMap=getUseInfo.Openid_userinfo(openid);
				if(useMap.isEmpty()){
					//access_token is invalid or not latest hint: [ZMsjDa0202vr43!]
					throw new ParametersException("access_token is invalid");
				}
				User openuser=new User();
				openuser.setIsSubscribe(Integer.parseInt(useMap.get("subscribe")));
				openuser.setHeadImg(useMap.get("headimgurl"));
				//用户名含有表情字符
				//### Error updating database.  Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data truncated for column 'nickname' at row 1
				//此处 替换emoji表情
				String nickName=useMap.get("nickname");
				logger.error(nickName);
				if(nickName != null)
		         {
		             Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
		             Matcher emojiMatcher = emoji.matcher(nickName);
		             if ( emojiMatcher.find()) 
		             {
		            	 nickName = emojiMatcher.replaceAll("*");
		             }
		        }
				openuser.setNickName(nickName);
				openuser.setOpenId(useMap.get("openid"));
				openuser.setSex(Integer.parseInt(useMap.get("sex")));
				openuser.setSubscribeTime(DateUtil.dateToTimestamp(new Date()));
				openuser.setLastAccessTime(DateUtil.dateToTimestamp(new Date()));
				if(openUserMapper.queryUserIsExists(openid)==0){

					openUserMapper.insertUsersInfo(openuser);}
				else{
					openuser.setIsSubscribe(1);
					openUserMapper.updateUsersInfo(openuser);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//ZndEnM8Qbs28_uB8AVQmfmizpL2XxB_164eOl_Uo6ks
			try {
					/*JSONObject params=new JSONObject();
					params.put("type", "news");
					params.put("offset", 0);
					params.put("count", 1);
					String result=HttpUtils.sendPostBuffer("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+GlobalConstantsPropertiesUtil.getString("access_token"), params.toJSONString());
					JSONObject jsonResult=JSONObject.parseObject(result);
					JSONArray item=(JSONArray) jsonResult.get("item");
					JSONObject media=(JSONObject)item.get(0);
					String mediaId=media.getString("media_id");
					JSONObject content=media.getJSONObject("content");
					JSONArray newsItems=content.getJSONArray("news_item");
					List<Article> list=new ArrayList<Article>();
					for(int i=0;i<newsItems.size();i++){
						Article article=new Article();
						JSONObject o=(JSONObject) newsItems.get(i);
						logger.error(o.getString("url"));
						logger.error(URLEncoder.encode(o.getString("url")));
						article.setUrl(o.getString("url"));
						article.setTitle(o.getString("title"));
						article.setPicUrl(o.getString("thumb_url"));
						list.add(article);
					}*/
					HashMap<String, String> userinfo=WeixinUseInfo.Openid_userinfo(openid);
					Article article=new Article();
					article.setDescription("商用云空间”是国内领先的商用物业互联网租赁平台，通过房地产大数据、移动互联网等技术为您提供商用物业快速交易匹配和精准选址服务。"); //图文消息的描述
					article.setPicUrl("http://www.iccspace.cn/upload/1444963246893890.jpg");
					//http://mmbiz.qpic.cn/mmbiz_png/ou9ZhzHqwny5B4oZ99tJbIicBLW2MkCnQBib62q05QJe3rjQGxMTosWZ4iaE3AqPyZE9kibNIsdaYqbCX1WE7WUayw/0?wx_fmt=png
					article.setTitle("商用云空间”是国内领先的商用物业互联网租赁平台，通过房地产大数据、移动互联网等技术为您提供商用物业快速交易匹配和精准选址服务。");  //图文消息标题
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxODUzNTE3Mw==&mid=100000007&idx=1&sn=3a5ddd2beef4fec0aec6b590d727e84a&chksm=17e8456d209fcc7b81033760b2348f9a5f2d5db01f43ff1a958ba8499f85bafb174bc33f89a3#rd");  //图文url链接
					Article article2=new Article();
					article2.setDescription("免费发布需求，智能推荐物业或租户"); //图文消息的描述
					article2.setPicUrl("http://www.iccspace.cn/upload/371593850825690987.jpg");
					article2.setTitle("免费发布需求，智能推荐物业或租户");  //图文消息标题
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxODUzNTE3Mw==&mid=100000007&idx=1&sn=3a5ddd2beef4fec0aec6b590d727e84a&chksm=17e8456d209fcc7b81033760b2348f9a5f2d5db01f43ff1a958ba8499f85bafb174bc33f89a3#rd");  //图文url链接
					Article article3=new Article();
					article3.setDescription("快速寻找经营空间或优质租户"); //图文消息的描述
					article3.setPicUrl("http://www.iccspace.cn/upload/371593850825690987.jpg");
					article3.setTitle("快速寻找经营空间或优质租户");  //图文消息标题
					article3.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxODUzNTE3Mw==&mid=100000007&idx=1&sn=3a5ddd2beef4fec0aec6b590d727e84a&chksm=17e8456d209fcc7b81033760b2348f9a5f2d5db01f43ff1a958ba8499f85bafb174bc33f89a3#rd");  //图文url链接
					Article article4=new Article();
					article4.setDescription("利用大数据选址工具精准分析"); //图文消息的描述
					article4.setPicUrl("http://www.iccspace.cn/upload/371593850825690987.jpg");
					article4.setTitle("利用大数据选址工具精准分析");  //图文消息标题
					article4.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxODUzNTE3Mw==&mid=100000007&idx=1&sn=3a5ddd2beef4fec0aec6b590d727e84a&chksm=17e8456d209fcc7b81033760b2348f9a5f2d5db01f43ff1a958ba8499f85bafb174bc33f89a3#rd");  //图文url链接
					Article article5=new Article();
					article5.setDescription("专业顾问支持，找店、转店、合租，so easy !"); //图文消息的描述
					article5.setPicUrl("http://www.iccspace.cn/upload/371593850825690987.jpg");
					article5.setTitle("专业顾问支持，找店、转店、合租，so easy !");  //图文消息标题
					article5.setUrl("http://mp.weixin.qq.com/s?__biz=MzIxODUzNTE3Mw==&mid=100000007&idx=1&sn=3a5ddd2beef4fec0aec6b590d727e84a&chksm=17e8456d209fcc7b81033760b2348f9a5f2d5db01f43ff1a958ba8499f85bafb174bc33f89a3#rd");  //图文url链接
					List<Article> list=new ArrayList<Article>();
					list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
					list.add(article2);
					list.add(article3);
					list.add(article4);
					list.add(article5);
					newmsg.setArticleCount(list.size());
					newmsg.setArticles(list);
					return MessageUtil.newsMessageToXml(newmsg);
			} catch (Exception e) {
			}
		
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			User openUser=openUserMapper.getUserInfoByOpenId(openid);
			openUser.setIsSubscribe(0);
			openUserMapper.updateUsersInfo(openUser);
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { // 扫描二维码事件
			System.out.println("==============这是扫描二维码事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { // 位置上报事件
			System.out.println("==============这是位置上报事件！");
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
			
			//openId 挂URL通过参数传递
			
			if(map.get("EventKey").equals("image")){
				Image img = new Image();
				HttpPostUploadUtil util=new HttpPostUploadUtil();
				String filepath="H:\\1.jpg";  
		        Map<String, String> textMap = new HashMap<String, String>();  
		        textMap.put("name", "testname");  
		        Map<String, String> fileMap = new HashMap<String, String>();  
		        fileMap.put("userfile", filepath); 
				String mediaidrs = util.uploadFile(textMap, fileMap);
				System.out.println(mediaidrs);
				String mediaid=JSONObject.parseObject(mediaidrs).getString("media_id");
				img.setMediaId(mediaid);
				imgmsg.setImage(img);
				return MessageUtil.imageMessageToXml(imgmsg);
			}else if(map.get("EventKey").equals("text")){
			    System.out.println("这里是回复图文消息!");
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
		}

		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { // 自定义菜单View事件
			System.out.println("==============这是自定义菜单View事件！");
			
		}

		return null;
	}
}
