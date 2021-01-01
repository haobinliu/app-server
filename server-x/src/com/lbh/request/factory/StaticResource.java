package com.lbh.request.factory;

/**
 * Copyright..@lbhbinhao@163.com
 * Author:liubinhao
 * Date:2020/12/29
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

public class StaticResource {

    public static final Integer FILE_RESOURCE = 0;
    public static final Integer CODE_RESOURCE = 1;

    private String uri;
    /**
     * 0 文件资源
     * 1 uri路径
     */
    private Integer type;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public StaticResource(String uri, Integer type) {
        this.uri = uri;
        this.type = type;
    }

    @Override
    public String toString() {
        return "StaticResource{" +
                "uri='" + uri + '\'' +
                ", type=" + type +
                '}';
    }
}
