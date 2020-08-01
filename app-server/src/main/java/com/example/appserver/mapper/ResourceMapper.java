package com.example.appserver.mapper;

import com.example.appserver.model.entity.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}