package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RequestMapping("/resource")
public class ResourceController {

    @PostMapping("/upload")
    public CommonResponse uploadFile(@RequestBody MultipartFile file){
        return CommonResponse.ok();
    }
}
