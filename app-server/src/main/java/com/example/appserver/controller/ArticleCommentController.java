package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.entity.ArticleComment;
import com.example.appserver.model.req.DropCommentReq;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RequestMapping("/comment")
@RestController
public class ArticleCommentController {

    @PostMapping("/publish")
    public CommonResponse publishComment(@RequestBody ArticleComment comment){
        return CommonResponse.ok();
    }

    @PostMapping("/drop")
    public CommonResponse dropComment(@RequestBody DropCommentReq req){
        return CommonResponse.ok();
    }

    @PostMapping("/favor")
    public CommonResponse favorComment(@RequestBody DropCommentReq req){
        return CommonResponse.ok();
    }
}
