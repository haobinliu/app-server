package server.http.response.handler;

import server.http.request.HttpReceivedMsg;
import server.http.response.HttpResponse;

import java.nio.Buffer;

/**
 * @author liubinhao
 * @date 2020/8/16
 */

public class ResponseFactory implements ResponseHandler{

    @Override
    public HttpResponse handle(HttpReceivedMsg msg, Buffer buffer) {
        if (msg.getHeaderMsg().get("Range")!=null){
            return new HttpRangerHandler().handle(msg,buffer);
        }
        else{
            return new DefaultHttpHandler().handle(msg, buffer);
        }
    }

    private static class SingletonHolder {
        private static final ResponseFactory INSTANCE = new ResponseFactory();
    }

    private ResponseFactory (){}

    public static ResponseFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
