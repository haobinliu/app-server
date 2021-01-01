package com.lbh.response;

import com.lbh.request.Req;
import com.lbh.request.factory.ResourceFactory;
import com.lbh.request.factory.StaticResource;
import com.lbh.utils.PropertiesReader;
import com.lbh.utils.ServerException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
/**
 * Copyright(c)lbhbinhao@ruigushop.com
 *
 * @author liubinhao
 * @date 2020/12/31
 * ++++ ______                           ______             ______
 * +++/     /|                         /     /|           /     /|
 * +/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |___________________   |     |____________|     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 */

public abstract class AbstractRespParser {

    public Resp parse(Req req) throws ServerException, IOException {
        //检查uri
        StaticResource resource = checkUri(req.getUri());
        //获取默认resp
        Resp resp = defaultResp();
        if (resource.getType().equals(StaticResource.FILE_RESOURCE)){
            resp.getHeaders().put("Content-Type", PropertiesReader.readProperties(
                    RespUtils.getType(req.getUri())));
            if (req.getHeaders().get("Range") != null){
                partialFile(resp,req);
                resp.setTopRow(Symbol.TOP_ROW_206);
            }
            else{
                integerFile(resp, req);
                resp.setTopRow(Symbol.TOP_ROW_200);
            }
        }
        /**
         * 用于做代码层面的接口
         */
        else{
        }
        return resp;
    }

    public Resp defaultResp(){
        Resp resp = new Response();
        resp.getHeaders().put(Symbol.ALLOW_METHOD,Symbol.REQUEST_METHOD);
        resp.getHeaders().put(Symbol.ALLOW_ORIGIN,Symbol.NORMAL);
        resp.getHeaders().put(Symbol.CONNECTION, Symbol.KEEP_ALIVE);
        resp.getHeaders().put(Symbol.RES_DATE, new Date().toString());
        resp.getHeaders().put(Symbol.SERVER_X, "server-lbh");
        return resp;
    }

    public StaticResource checkUri(String uri) throws ServerException {
        if(!ResourceFactory.resourceFactory.containsKey(uri)){
            throw new ServerException("No such resource",400);
        }
        return ResourceFactory.resourceFactory.get(uri);
    }

    public static String RESOURCE_PATH = ResourceFactory.RESOURCE_PATH;

    public void integerFile(Resp resp, Req req) throws IOException {
        String uri = req.getUri();
        File file = new File(RESOURCE_PATH + uri);
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        channel.read(buffer);
        buffer.rewind();
        resp.getHeaders().put(Symbol.CONTENT_LENGTH, String.valueOf(buffer.capacity()));
        resp.setBody(buffer.array());
    }

    public void partialFile(Resp resp, Req req) throws IOException {
        String uri = req.getUri();
        File file = new File(RESOURCE_PATH + uri);
        RandomAccessFile r  = null;
        r = new RandomAccessFile(file, "r");
        RespUtils.Range range = RespUtils.requestRange(req.getHeaders().get("Range"), (int) file.length());
        if (range.getSize()!=0) {
            byte[] bytes = new byte[(int) (range.getOffset() - range.getStart())];
            r.seek(range.getStart());
            r.read(bytes);
            System.out.println("分段bytes长度为:" + bytes.length);
            resp.getHeaders().put(Symbol.CONTENT_LENGTH, "" + bytes.length);
            resp.getHeaders().put(Symbol.CONTENT_RANGE,"bytes " + range.getStart() + "-" + (range.getOffset()) + "/" + range.getSize());
            resp.setBody(bytes);
        }
    }



}
