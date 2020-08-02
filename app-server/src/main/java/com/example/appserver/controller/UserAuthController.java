package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.common.LoginUtil;
import com.example.appserver.model.req.UserAuthReq;
import com.example.appserver.model.vo.UserAuthRes;
import com.example.appserver.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthServicel;
    @PostMapping("/login")
    public CommonResponse login(@RequestBody UserAuthReq req){
        //对密码进行解密并且进行二次加密
        UserAuthReq userAuthReq = LoginUtil.loginAuth(req);

        UserAuthRes userAuthRes = new UserAuthRes(this.userAuthServicel.login(userAuthReq));

        return CommonResponse.ok(userAuthRes);
    }

    @PostMapping("/regist")
    public CommonResponse regist(@RequestBody UserAuthReq req){

        return CommonResponse.ok();
    }
}
