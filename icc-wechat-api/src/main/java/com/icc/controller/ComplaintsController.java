package com.icc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;
import com.icc.common.Constants;
import com.icc.entity.BaseEntity;
import com.icc.entity.Complaints;
import com.icc.entity.UserSessionDto;
import com.icc.service.ComplaintService;
import com.icc.util.DateUtil;

@Controller
@RequestMapping("/complaints")
@SessionAttributes({Constants.SESSION_USER})
public class ComplaintsController {

	@Autowired
	private ComplaintService complaintService;

	@RequestMapping(method=RequestMethod.POST,value="/insertUsersComplaint")
	public @ResponseBody String addUsersComplaint(Complaints com,ModelMap model) {
		BaseEntity be=new BaseEntity();
		UserSessionDto user=(UserSessionDto) model.get(Constants.SESSION_USER);
		
		if(com.getComplaintUserId()==null && ("").equals(com.getComplaintUserId())){
			com.setComplaintUserId(user.getUserId());
		}
		com.setComplaintTime(DateUtil.dateToTimestamp(new Date()));
		int result = complaintService.insertUsersComplaint(com);
		if (result > 0) {
			be.getHead().put(Constants.CODE, "ok");
			be.getHead().put(Constants.MESSAGE, "0000");
		} else {
			be.getHead().put(Constants.CODE, "99");
			be.getHead().put(Constants.MESSAGE, "ERROR");
		}
		return JSONObject.toJSONString(be);
	}
}
