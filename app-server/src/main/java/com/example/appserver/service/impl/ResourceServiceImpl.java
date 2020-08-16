package com.example.appserver.service.impl;

import com.example.appserver.model.entity.Resource;
import com.example.appserver.service.ResourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
public class ResourceServiceImpl implements ResourceService {

    @Value("${}")
    private String filePath;

    @Override
    public Resource uploadFile(MultipartFile file) throws IOException {
        Resource resource = new Resource();
        String fileName = file.getOriginalFilename();
        String s = UUID.randomUUID().toString();
        String[] f = Objects.requireNonNull(file.getOriginalFilename()).split(".");
        String fileType = f[f.length-1];
        String fileUrl = filePath+s+"."+fileType;
        file.transferTo(new File(fileUrl));
        resource.setCreateTime(new Date());
        resource.setResourceStatus(true);
        resource.setResourceUrl(fileUrl);
        return resource;
    }
}
