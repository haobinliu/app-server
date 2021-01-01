package com.lbh.utils;

import com.lbh.server.ExtremeServer;

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

public class ServerException extends Exception {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServerException(String message, int code) {
        super(message);
        this.code = code;
    }
}
