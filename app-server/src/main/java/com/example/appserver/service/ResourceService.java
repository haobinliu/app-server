package com.example.appserver.service;

import com.example.appserver.model.entity.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
public interface ResourceService {

    /**
     * 上传文件
     * @param file 文件
     * @return 成功返回资源信息
     */
    Resource uploadFile(MultipartFile file);
}
