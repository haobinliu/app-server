package com.example.appserver.mapper;

import com.example.appserver.model.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}