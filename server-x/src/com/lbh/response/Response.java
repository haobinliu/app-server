package com.lbh.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

public class Response implements Resp{

    private String topRow;

    private Map<String, String> headers = new HashMap<>(12);

    private byte[] body;

    private int responseCode;

    @Override
    public String getTopRow() {
        return topRow;
    }

    @Override
    public void setTopRow(String topRow) {
        this.topRow = topRow;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    @Override
    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public static final byte[] LINE_SEPARATE_SIGN_BYTES    = "\r\n".getBytes();
    public static final byte[] KV_SEPARATE_SIGN_BYTES      = ":".getBytes();

    private SocketChannel channel;

    @Override
    public SocketChannel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

    //    如果头数据里不包含Content-Length: x 类型为Transfer-Encoding:
//    chunked 说明响应数据的长度不固定，结束符已"\r\n0\r\n"这5个字节为结束符
    public void service() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024*5+body.length);
        buffer.put(this.getTopRow().getBytes());
        buffer.put(LINE_SEPARATE_SIGN_BYTES);
        for (Map.Entry<String, String> entry : this.getHeaders().entrySet()){
            buffer.put(entry.getKey().getBytes());
            buffer.put(KV_SEPARATE_SIGN_BYTES);
            buffer.put(entry.getValue().getBytes());
            buffer.put(LINE_SEPARATE_SIGN_BYTES);
        }
        buffer.put(LINE_SEPARATE_SIGN_BYTES);
        buffer.put(this.getBody());
        buffer.flip();
        this.channel.write(buffer);
    }

    @Override
    public void run() {
        try {
            this.service();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "topRow='" + topRow + '\'' +
                ", headers=" + headers +
//                ", body=" + Arrays.toString(body) +
                ", responseCode=" + responseCode +
                ", channel=" + channel +
                '}';
    }
}
