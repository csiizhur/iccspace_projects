package com.icc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icc.dao.ComplaintsDao;
import com.icc.entity.Complaints;
import com.icc.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintsDao complaintDao;
	@Override
	public int insertUsersComplaint(Complaints com) {
		return complaintDao.insertUsersComplaint(com);
	}

}
