package com.lbh.client;

import com.lbh.request.Req;
import com.lbh.request.ReqUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

/**
 * Copyright..@lbhbinhao@163.com
 * Author:liubinhao
 * Date:2020/12/28
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


public class EndPoint implements Callable<Req> {

    private static final String CONNECTION = "Connection";
    private static final String CONNECTION_KEEP_ALIVE = "keep-alive";

    private SelectionKey key;

    public EndPoint(SelectionKey key) {
        this.key = key;
    }

    @Override
    public Req call() throws Exception {
        String reqMsg = handleNewClientMessage(this.key);
//        System.err.println("reqMsg:"+reqMsg);
        Req request = null;
        if (!reqMsg.isBlank()&&!reqMsg.isEmpty()) {
            request = ReqUtils.parse(reqMsg);
            System.err.println(request);
        }
        return request;
    }

    private String handleNewClientMessage(SelectionKey key) throws IOException {
        SocketChannel sendingChannel = (SocketChannel) key.channel();
        String reqMsg = "";
        ByteBuffer receivingBuffer = ByteBuffer.allocate(2048);
        int bytesRead = sendingChannel.read(receivingBuffer);
        if (bytesRead > 0) {
            receivingBuffer.flip();
            byte[] array = new byte[receivingBuffer.limit()];
            receivingBuffer.get(array);
            reqMsg = new String(array);
            System.out.println("Server received "  +reqMsg);
        }
        return reqMsg;
    }

}
