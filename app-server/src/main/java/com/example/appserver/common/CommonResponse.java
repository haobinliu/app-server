package com.example.appserver.common;

import lombok.Data;

/**
 * @author liubinhao
 * @date 2020/7/11
 *
 * @Description
 *
 *General response class
 *code  200 success
 *      500 error
 */

@Data
public class CommonResponse<T> {
    private Integer code;
    private String  msg;
    private T       data;

    public CommonResponse(T data){
        this.code = 200;
        this.msg  = "success";
        this.data = data;
    }

    public CommonResponse(Integer code,String msg,T data){
        this.code = code;
        this.msg  = msg;
        this.data = data;
    }

    public CommonResponse succ(T data){
        return new CommonResponse(data);
    }

    public CommonResponse fail(String msg){
        return new CommonResponse(500,msg,null);
    }
}
