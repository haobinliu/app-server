package com.lbh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ++++ ______ @author       liubinhao   ______             ______
 * +++/     /|                         /     /|           /     /|
 * +/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |                      |     |            |     |   |
 * |     |   |                      |     |____________|     |   |
 * |     |   | ___________________  |     |   |        |     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/   |  |     |   |        |     |   |
 * |                         |  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 *
 * @date 2020/9/23
 */

public class PropertiesReader {

    private static final String MIME_PROPERTIES_LOCATION =
            "server-x\\src\\com\\lbh/utils\\contentype.properties";

    public static String readProperties(String key) {
        Properties prop = new Properties();
        try {prop.load(new FileInputStream(MIME_PROPERTIES_LOCATION));
                return prop.get(key).toString();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }
}
