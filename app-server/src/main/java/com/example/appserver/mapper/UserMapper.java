package com.example.appserver.mapper;

import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;

public interface UserMapper {

    User checkUserinfo(UserAuthReq userAuthReq);

    Integer registCheck(UserAuthReq userAuthReq);

    Integer insertSelective(User user);
}