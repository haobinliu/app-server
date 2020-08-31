package server;

import server.http.request.HttpReceivedMsg;
import server.http.error.ResourceNotFound;
import server.http.response.HttpResponse;
import server.http.response.handler.ResponseFactory;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liubinhao
 * @date 2020/8/5
 */
public class FileOutputHandler implements Runnable {

    private static final String HAS_RANGE = "Range";

    private ExecutorService res = Executors.newCachedThreadPool();

    public static String FILE_PATH;

    private Socket connection;

    private HttpReceivedMsg msg;

    public FileOutputHandler(Socket connection, HttpReceivedMsg msg) {
        this.connection = connection;
        this.msg = msg;
    }

    @Override
    public void run() {
        String requestUri = msg.getRequestUri();
        File file = new File(FILE_PATH + requestUri);
        System.out.println(FILE_PATH + requestUri);
        FileInputStream fis = null;
        FileChannel channel = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            try {
                ResourceNotFound.noSuchFile(connection.getOutputStream());
                connection.close();
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
            System.out.println(buffer);
            channel.read(buffer);
            buffer.rewind();
            // obtain corresponding handler to write
            HttpResponse response = ResponseFactory.getInstance().handle(msg, buffer);
            response.setOutputStream(connection.getOutputStream());
            response.out();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
