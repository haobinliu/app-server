package server;

import server.http.request.HttpMsgParser;
import server.http.request.HttpReceivedMsg;
import java.io.InputStream;
import java.net.Socket;
import java.security.spec.PSSParameterSpec;
import java.util.concurrent.Callable;

/**
 * @author liubinhao
 * @date 2020/8/5
 */

public class ConnectionHandler implements Callable<HttpReceivedMsg> {

    private Socket connection;
    public ConnectionHandler(Socket connection){
        this.connection = connection;
    }

    @Override
    public HttpReceivedMsg call() throws Exception {
        InputStream inputStream  = connection.getInputStream();
        byte[] httpRequestStream = new byte[1024];
        int read = inputStream.read(httpRequestStream);
        StringBuilder httpRequestMessage = new StringBuilder();
        if (read != -1) {
            for (byte b:httpRequestStream) {
                httpRequestMessage.append((char)b);
            }
        }
        HttpMsgParser<HttpReceivedMsg> httpMsgParser = new HttpReceivedMsg();
        return httpMsgParser.parse(httpRequestMessage.toString());
    }
}
