package com.example.appserver.model.vo;

import com.example.appserver.model.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author dsl
 */
@Data
public class UserAuthRes {

    private Integer userId;

    private String  nickName;

    private String  email;

    private String  phone;

    private String  city;

    private Date    birthday;

    private Integer sex;

    private Byte    age;

    private Boolean userStatus;

    private String  token;

    public UserAuthRes(User user){
        this.userId     = user.getUserId();
        this.nickName   = user.getNickName();
        this.email      = user.getEmail();
        this.phone      = user.getPhone();
        this.city       = user.getCity();
        this.birthday   = user.getBirthday();
        this.sex        = user.getSex();
        this.userStatus = user.getUserStatus();
    }

}
