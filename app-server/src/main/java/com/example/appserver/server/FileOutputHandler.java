package com.example.appserver.server;
import com.example.appserver.server.http.HttpReceivedMsg;
import lombok.Data;
import lombok.SneakyThrows;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liubinhao
 * @date 2020/8/5
 */
@Data
public class FileOutputHandler implements Runnable {

    private static final String FILE_PATH = "D:/image";

    private Socket connection;

    private HttpReceivedMsg msg;

    public FileOutputHandler(Socket connection,HttpReceivedMsg msg){
        this.connection = connection;
        this.msg = msg;
    }
    @SneakyThrows
    @Override
    public void run() {
        String requestUri = msg.getRequestUri();
        File file = new File(FILE_PATH+requestUri);
        System.out.println(FILE_PATH+requestUri);
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        OutputStream outputStream = connection.getOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate((int)file.length());

        channel.read(buffer);
        outputStream.write(buffer.array());
        channel.close();
        fis.close();
    }


}
