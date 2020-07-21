package com.example.appserver.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ArchUser {
    /**用户id*/
    private int userId;

    /**用户名*/
    private String userName;

    /**用户密码*/
    private String password;

    /**邮箱*/
    private String email;

    /**手机号码*/
    private String phone;

    /**用户信息更新时间*/
    private Date modifyTime;

    /**用户创建时间*/
    private Date createTime;

    /**本次登录时间*/
    private int loginTime;

    /**登录次数*/
    private int loginCount;

    /**错误次数*/
    private int err_time;

    /**锁定时间*/
    private Date lockDate;

}
