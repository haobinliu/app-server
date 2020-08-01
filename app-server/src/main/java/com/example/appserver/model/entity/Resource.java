package com.example.appserver.model.entity;

import java.util.Date;

public class Resource {
    private Integer resourceId;

    private String resourceName;

    private String resourceUrl;

    private Date createTime;

    private Integer userId;

    private Boolean resourceStatus;

    private Date updateTime;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Boolean resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}