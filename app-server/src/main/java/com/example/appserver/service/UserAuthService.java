package com.example.appserver.service;

import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;

public interface UserAuthService {

    public User login(UserAuthReq userAuthReq);
}
