package com.example.appserver.model.req;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@Data
public class UserAuthReq {
    /**
     * 登录
     */
    private String nickName;
    private String passWord;

    /**
     * 注册
     */
    private String phone;

    private String email;

    private Integer sex;

    private Integer age;
    //非必填
    private Date birthday;
    //非必填
    private String city;


}
