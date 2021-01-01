package com.lbh.response;

import java.io.IOException;
import java.nio.channels.SocketChannel;
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
public interface Resp extends Runnable{

    String getTopRow();

    void setTopRow(String topRow);

    Map<String, String> getHeaders();

    void setHeaders(Map<String, String> headers);

    byte[] getBody();

    void setBody(byte[] body);

    SocketChannel getChannel();

    void setChannel(SocketChannel channel);
}
