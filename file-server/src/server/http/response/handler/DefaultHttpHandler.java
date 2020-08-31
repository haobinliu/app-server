package server.http.response.handler;

import server.http.request.HttpReceivedMsg;
import server.http.response.HttpResponse;
import server.http.response.PlainResponseHeader;
import server.http.response.ResponseHeader;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author liubinhao
 * @date 2020/8/16
 */
public class DefaultHttpHandler implements ResponseHandler {
    @Override
    public HttpResponse handle(HttpReceivedMsg msg, Buffer buffer) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setHttpTopRow(ResponseHeader.DEFAULT_RESPONSE_HEAD_LINE);
        byte[] bytes1 = new PlainResponseHeader().generateHeader(getType(msg), buffer.capacity());
        httpResponse.setHttpHeader(bytes1);
        if (buffer instanceof ByteBuffer) {
            httpResponse.setHttpBody(((ByteBuffer) buffer).array());
        }
        return httpResponse;
    }

    /**
     * 获取请求的文件
     * @param msg 请求内容
     * @return 请求文件类型
     */
    public static String getType(HttpReceivedMsg msg){
        String requestUri = msg.getRequestUri();
        String[] fileName;
        if (requestUri.contains(".")) {
            fileName = requestUri.split("\\.");
            return fileName[fileName.length - 1];
        }
        return null;
    }

}
