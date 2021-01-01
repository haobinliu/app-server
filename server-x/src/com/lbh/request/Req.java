package com.lbh.request;

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
public interface Req {

    String getMethod();

    String getUri();

    String getVersion();

    byte[] getBody();

    Map<String, String> getHeaders();

    void setMethod(String method);

    void setUri(String uri);

    void setVersion(String version);

    void setHeaders(Map<String, String> headers);

    void setBody(byte[] body);

}
