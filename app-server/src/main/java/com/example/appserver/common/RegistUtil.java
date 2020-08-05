package com.example.appserver.common;

import com.example.appserver.model.req.UserAuthReq;

/**
 * @author dsl
 */
public class RegistUtil {

    public static UserAuthReq registEncode(UserAuthReq userAuthReq){
        userAuthReq.setPassWord(EncodeUtil.md5(new String(userAuthReq.getPassWord())));
        return userAuthReq;
    }
}
