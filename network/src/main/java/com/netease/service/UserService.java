package com.netease.service;

import com.netease.entity.User;

//service层接口
public interface UserService {

	//检验用户登录
	User checkLogin(String userName,String password);
}
