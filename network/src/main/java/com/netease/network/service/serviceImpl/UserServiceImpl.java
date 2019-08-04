package com.netease.network.service.serviceImpl;

import com.netease.network.entity.User;
import com.netease.network.mapper.UserDao;
import com.netease.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserDao userDao;

    @Override
    public User findUserByname(String userName) {
        return userDao.findUserByname(userName);
    }
}
