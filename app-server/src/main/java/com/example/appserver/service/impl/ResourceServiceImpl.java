package com.example.appserver.service.impl;

import com.example.appserver.model.entity.Resource;
import com.example.appserver.service.ResourceService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
public class ResourceServiceImpl implements ResourceService {

    @Override
    public Resource uploadFile(MultipartFile file) {
        return new Resource();
    }
}
