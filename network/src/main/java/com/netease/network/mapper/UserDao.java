package com.netease.network.mapper;

import com.netease.network.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User findUserByname(String userName);
}
