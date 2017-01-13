package cn.iccspace.message.controller;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import com.google.gson.GsonBuilder;

import cn.iccspace.entity.UserInfo;
import cn.iccspace.message.websocket.model.Message;
import cn.iccspace.message.service.UserService;
import cn.iccspace.message.websocket.MyWebSocketHandler;
@Controller
@RequestMapping("/msg")
public class SendMsgController {
	@Resource
	MyWebSocketHandler handler;
	Map<String, UserInfo> users = new HashMap<String, UserInfo>();
	
	@Autowired
	private UserService userService;
	// 模拟一些数据
	@ModelAttribute
	public void setReqAndRes() {
		UserInfo u1 = new UserInfo();
		u1.setUserId("c75c9c61a25011e6b0d000163e020977");
		u1.setNickName("张三");
		users.put(u1.getUserId(), u1);
		UserInfo u2 = new UserInfo();
		u2.setUserId("89030bdca25211e6b0d000163e020977");
		u2.setNickName("李四");
		users.put(u2.getUserId(), u2);
	}
	// 模拟一些数据
	@ModelAttribute
	public void setReqAndRes2() {
		List<UserInfo> list=userService.getAllUsers();
		for(UserInfo u:list){
			users.put(u.getUserId(), u);
		}
	}
	// 用户登录
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView doLogin(UserInfo user, HttpServletRequest request) {
		request.getSession().setAttribute("uid", user.getUserId());
		request.getSession().setAttribute("name",users.get(user.getUserId()).getNickName());
		return new ModelAndView("redirect:talk");
	}

	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public ModelAndView talk() {
		return new ModelAndView("talk");
	}

	// 跳转到发布广播页面
	@RequestMapping(value = "broadcast", method = RequestMethod.GET)
	public ModelAndView broadcast() {
		return new ModelAndView("broadcast");
	}

	// 发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(String text) throws IOException {
		Message msg = new Message();
		msg.setDate(new Date());
		msg.setFrom("");
		msg.setFromName("系统广播");
		msg.setTo("");
		msg.setText(text);
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}
}