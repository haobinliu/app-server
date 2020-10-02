package server;

import server.http.request.HttpReceivedMsg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
/**
 * @author liubinhao
 * @date 2020/8/5
 * //我们整个服务的管理  初始化配置
 *
 * // 连接进来-> 管理进来的连接  （主线程才能接收到连接）--->分发给处理的线程/分给给其他端口去处理
 *
 * // 其他线程具体去处理的步骤/<></> 有相应的记录 （处理-->失败的应该）
 *
 * // 相应成功了
 */
public class FileServer {

    private static final Integer CORE_POOL_SIZE = 1;
    private static final Integer MAXIMUM_POOL_SIZE = 5;
    private static final Long    KEEP_ALIVE_TIME = 50000L;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;

    private volatile boolean deamon = true;

    private BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>();
    private ServerSocket server;

    private ExecutorService preHandle  = Executors.newCachedThreadPool();
    private ExecutorService fileHandle = Executors.newCachedThreadPool();

    public FileServer(Integer port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public void serverStart() throws IOException {

        while (deamon){
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
