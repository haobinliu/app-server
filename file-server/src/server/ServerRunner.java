package server;

import server.common.ConfigInitializer;
import java.io.IOException;
import java.util.List;

/**
 * @author liubinhao
 * @date 2020/8/5
 */
public class ServerRunner {

    public static List<Integer> ports;

    public static void main(String[] args) throws IOException {
        ConfigInitializer.initializeConfig();
        FileServer fileServer = new FileServer(ports.get(0));
        fileServer.serverStart();
    }

}
