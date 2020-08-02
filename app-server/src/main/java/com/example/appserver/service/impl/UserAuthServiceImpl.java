package com.example.appserver.service.impl;

import com.example.appserver.mapper.UserMapper;
import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;
import com.example.appserver.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserAuthReq userAuthReq) {
        User user = this.userMapper.checkUserinfo(userAuthReq);
        return user;
    }
}
