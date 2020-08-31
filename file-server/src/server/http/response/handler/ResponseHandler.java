package server.http.response.handler;

import server.http.request.HttpReceivedMsg;
import server.http.response.HttpResponse;

import java.nio.Buffer;

/**
 * @author liubinhao
 * @date 2020/8/16
 */
public interface ResponseHandler {
    /**
     * 处理请求
     * @param msg 接受的http请求
     * @param buffer response body
     * @return    httpResponse
     */
    HttpResponse handle(HttpReceivedMsg msg, Buffer buffer);
}
