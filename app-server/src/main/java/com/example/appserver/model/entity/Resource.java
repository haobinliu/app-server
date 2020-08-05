package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author liubinhao
 */
@Data
public class Resource {
    private Integer resourceId;

    private String  resourceName;

    private String  resourceUrl;

    private Date    createTime;

    private Integer userId;

    private Boolean resourceStatus;

    private Date    updateTime;

}