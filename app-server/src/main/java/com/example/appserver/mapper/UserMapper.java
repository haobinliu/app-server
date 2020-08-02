package com.example.appserver.mapper;

import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;

public interface UserMapper {
    User checkUserinfo(UserAuthReq userAuthReq);

    int insert(User record);

    int insertSelective(User record);
}