package com.example.appserver.model.entity;

import com.example.appserver.model.req.UserAuthReq;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;

    private String nickName;

    private String passWord;

    private String email;

    private String phone;

    private String city;

    private Date birthday;

    private Integer sex;

    private Byte age;

    private Date createTime;

    private Date updateTime;

    private Boolean userStatus;


}