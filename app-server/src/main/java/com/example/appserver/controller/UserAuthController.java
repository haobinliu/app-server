package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.common.LoginUtil;
import com.example.appserver.common.RegistUtil;
import com.example.appserver.common.TokenManager;
import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;
import com.example.appserver.model.vo.UserAuthRes;
import com.example.appserver.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

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
        if(userAuthRes==null){
            return CommonResponse.ok("用户名或密码不正确，请重新输入！");
        }else{
            userAuthRes.setToken(TokenManager.createToken(userAuthRes.getUserId()));
            return CommonResponse.ok(userAuthRes);
        }
    }

    @PostMapping("/register")
    public CommonResponse regist(@RequestBody UserAuthReq req){
        UserAuthReq phoneUser = new UserAuthReq();
        UserAuthReq emailUser = new UserAuthReq();

        phoneUser.setPhone(req.getPhone());
        emailUser.setEmail(req.getEmail());

        Integer phoneRes = this.userAuthServicel.registCheck(phoneUser);
        Integer emailRes = this.userAuthServicel.registCheck(emailUser);

        if (phoneRes>0){
            return CommonResponse.fail("手机号已被注册!");
        }
        if (emailRes>0){
            return CommonResponse.fail("邮箱已被注册！");
        }
        req = RegistUtil.registEncode(req);

        Integer regisRes = this.userAuthServicel.register(req);
        return CommonResponse.ok(regisRes);
    }
}
