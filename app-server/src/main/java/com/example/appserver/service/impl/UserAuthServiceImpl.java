package com.example.appserver.service.impl;

import com.example.appserver.mapper.UserMapper;
import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;
import com.example.appserver.model.vo.UserAuthRes;
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

    @Override
    public Integer registCheck(UserAuthReq userAuthReq){
        return this.userMapper.registCheck(userAuthReq);
    }

    @Override
    public Integer register(UserAuthReq req){
        User user = new User();


        user.setNickName(req.getNickName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setCity(req.getCity());
        user.setBirthday(req.getBirthday());
        user.setSex(req.getSex());
        user.setPassWord(req.getPassWord());
        user.setUserStatus(true);

        Integer registRes = this.userMapper.insertSelective(user);

        return registRes;
    }
}
