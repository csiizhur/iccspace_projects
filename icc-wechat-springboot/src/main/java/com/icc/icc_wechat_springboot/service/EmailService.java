package com.icc.icc_wechat_springboot.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	    // 用于读取配置文件的信息,默认从resources目录下的application.properties文件中读取
	    @Value("${EmailQueueListener.enabled}")
	    public boolean EmailQueueListenerEnbaled;
	
	     @PostConstruct
	        public void init() {
	           // 初始化一些服务,例如队列服务等
	        }
	    
	     public List<String> sendMail(String title, String body, String... to) {
	         return null;
	     }
	}

