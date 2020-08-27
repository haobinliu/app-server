package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.service.ResourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RequestMapping("/resource")
public class ResourceController {
    @Resource
    private ResourceService resourceService;

    @PostMapping("/upload")
    public CommonResponse uploadFile(@RequestBody MultipartFile file) throws IOException {
        resourceService.uploadFile(file);
        return CommonResponse.ok();
    }
}
