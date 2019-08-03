package com.netease.dao;

import com.netease.entity.User;

public interface UserDao {
	
	User findByUserName(String userName);
}
