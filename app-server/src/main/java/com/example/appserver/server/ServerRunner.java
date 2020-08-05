package com.example.appserver.server;

import java.io.IOException;

/**
 * @author liubinhao
 * @date 2020/8/5
 */

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        FileServer fileServer = new FileServer(10001);
        fileServer.serverStart();
    }
}
