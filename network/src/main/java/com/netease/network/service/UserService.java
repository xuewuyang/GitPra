package com.netease.network.service;

import com.netease.network.entity.User;

public interface UserService {

    User findUserByname(String userName);
}
