package com.example.appserver.common;

import lombok.Data;

/**
 * @author liubinhao
 * @date 2020/7/11
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
    public CommonResponse(){
        this.code = 200;
        this.msg  = "success";
        this.data = null;
    }

    public static <R> CommonResponse<R> ok(R data){
        return new CommonResponse<R>(data);
    }

    public static <R> CommonResponse<R> ok(){
        return new CommonResponse<R>();
    }

    public static <R> CommonResponse<R> fail(String msg){
        return new CommonResponse<R>(500,msg,null);
    }

    public static <R> CommonResponse<R> fail(){
        return new CommonResponse<R>(500,"内部服务异常",null);
    }
}
