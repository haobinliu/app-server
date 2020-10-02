package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.entity.ArticleComment;
import com.example.appserver.model.req.DropCommentReq;
import com.example.appserver.service.ArticleCommentService;
import org.apache.ibatis.io.Resources;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.InputStream;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RequestMapping("/comment")
@RestController
public class ArticleCommentController {

    @Resource
    private ArticleCommentService articleCommentService;


    @PostMapping("/publish")
    public CommonResponse<ArticleComment> publishComment(@RequestBody ArticleComment comment){
        ArticleComment articleComment = articleCommentService.publishComment(comment);
        return CommonResponse.ok(articleComment);
    }

    @PostMapping("/drop")
    public CommonResponse<Boolean> dropComment(@RequestBody DropCommentReq req){
        boolean b = articleCommentService.dropComment(req);
        return CommonResponse.ok(b);
    }

    @PostMapping("/favor")
    public CommonResponse<Boolean> favorComment(@RequestBody DropCommentReq req){
        boolean b = articleCommentService.favorComment(req);
        return CommonResponse.ok(b);
    }

    @RequestMapping(value = "videos/file-name", method = GET)
    @ResponseBody
    public final ResponseEntity<InputStreamResource>
    retrieveResource(@PathVariable(value = "file-name")
                     final String fileName,
                     @RequestHeader(value = "Range", required = false)
                             String range) throws Exception {

        long rangeStart = Long.parseLong(range.replace("bytes=","").split("-")[0]);//parse range header, which is bytes=0-10000 or something like that
        long rangeEnd = Long.parseLong(range.replace("bytes=","").split("-")[0]);//parse range header, which is bytes=0-10000 or something like that


        InputStream inputStream = Resources.getResourceAsStream(fileName);//or read from wherever your data is into stream
        long contentLenght = 20;//you must have it somewhere stored or read the full file size
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Expires", "0");
        headers.set("Cache-Control", "no-cache, no-store");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Transfer-Encoding", "binary");
        headers.set("Content-Length", String.valueOf(rangeEnd - rangeStart));

//if start range assume that all content
        if (rangeStart == 0) {
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, OK);
        } else {
            headers.set("Content-Range", format("bytes %s-%s/%s", rangeStart, rangeEnd, contentLenght));
            return new ResponseEntity<>(new InputStreamResource(inputStream), headers, PARTIAL_CONTENT);
        }
    }
}
