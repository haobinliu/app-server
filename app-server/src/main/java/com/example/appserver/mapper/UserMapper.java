package com.example.appserver.mapper;

import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;

public interface UserMapper {
    User checkUserinfo(User user);

    int insert(User record);

    int insertSelective(User record);
}