package com.lbh.response;

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

public class DefaultRespParser extends AbstractRespParser{

    private volatile static DefaultRespParser singleton;

    private DefaultRespParser (){}
    public static DefaultRespParser getRespParser() {
        if (singleton == null) {
            synchronized (DefaultRespParser.class) {
                if (singleton == null) {
                    singleton = new DefaultRespParser();
                }
            }
        }
        return singleton;
    }

}
