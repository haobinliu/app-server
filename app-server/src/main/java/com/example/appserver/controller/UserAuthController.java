package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.req.UserAuthReq;
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

    @PostMapping("/login")
    public CommonResponse login(@RequestBody UserAuthReq req){
        return CommonResponse.ok();
    }

    @PostMapping("/regist")
    public CommonResponse regist(@RequestBody UserAuthReq req){
        return CommonResponse.ok();
    }
}
