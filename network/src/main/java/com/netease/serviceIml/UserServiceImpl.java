package com.netease.serviceIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.UserDao;
import com.netease.entity.User;
import com.netease.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	//检验用户登录服务
	@Override
	public User checkLogin(String userName, String password) {
		
		User user = userDao.findByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
