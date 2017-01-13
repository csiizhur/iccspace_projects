package cn.iccspace.message.service.impl;

import java.util.List;
import cn.iccspace.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.iccspace.entity.UserInfo;
import cn.iccspace.message.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoDao userDao;
	@Override
	public List<UserInfo> getAllUsers() {
		return userDao.queryAllUsers();
	}

}
