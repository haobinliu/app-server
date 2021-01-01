package com.lbh;

import com.lbh.request.factory.ResourceFactory;
import com.lbh.server.ExtremeServer;
import com.lbh.utils.ServerException;
import com.lbh.utils.ShowLogo;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Copyright..@lbhbinhao@163.com
 * Author:liubinhao
 * Date:2020/12/28
 * +++++ ______                           ______             ______
 * ++++/     /|                         /     /|           /     /|
 * ++/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |___________________   |     |____________|     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 */

public class BootStrap {

    static {
        ResourceFactory.init(ResourceFactory.RESOURCE_PATH);
    }


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, ServerException {
        ShowLogo.printLogo();
        ExtremeServer server = new ExtremeServer(80);
        ShowLogo.startSuccess();
        server.start();

    }

}
