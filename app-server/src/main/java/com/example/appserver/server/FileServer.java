package com.example.appserver.server;

import com.example.appserver.server.http.HttpReceivedMsg;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author liubinhao
 * @date 2020/8/5
 */
@Data
public class FileServer {

    private static final Integer CORE_POOL_SIZE = 1;
    private static final Integer MAXIMUM_POOL_SIZE = 5;
    private static final Long    KEEP_ALIVE_TIME = 50L;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    private BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>();
    private ServerSocket server;

    private Integer      port;

    private ExecutorService preHandle  = Executors.newCachedThreadPool();
    private ExecutorService fileHandle = Executors.newCachedThreadPool();

    public FileServer(Integer port) throws IOException {
        this.port   = port;
        this.server = new ServerSocket(port);
    }

    public void serverStart() throws IOException {
        while (true){
            try {
                Socket socket = this.server.accept();
                ConnectionHandler connectionHandler = new ConnectionHandler(socket);
                Future<HttpReceivedMsg> future = preHandle.submit(connectionHandler);
                HttpReceivedMsg httpReceivedMsg = future.get();
                System.out.println(httpReceivedMsg);
                FileOutputHandler fileOutputHandler = new FileOutputHandler(socket, httpReceivedMsg);
                fileHandle.submit(fileOutputHandler);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
