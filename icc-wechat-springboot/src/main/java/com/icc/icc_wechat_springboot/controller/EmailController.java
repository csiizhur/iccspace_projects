package com.icc.icc_wechat_springboot.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
public class EmailController {
	//@Autowired
    //private EmailService emailService;

    @ResponseBody
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendMail(String title, String content, String email) {
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "/sendMail2", method = RequestMethod.GET)
    public String sendMail2(String title, String content, String email) {
    	return "asfsdgg";
    }
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String sendMail3(@RequestBody List<MediaModel> media) {
    	return "asfsdgg";
    }
}
