package server.http.response.handler;

import server.http.properties.PropertiesConfig;
import server.http.request.HttpReceivedMsg;
import server.http.response.HttpResponse;
import server.http.response.ResponseHeader;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.IllegalFormatCodePointException;

/**
 * @author liubinhao
 * @date 2020/8/16
 */

public class HttpRangerHandler extends DefaultHttpHandler{
    private static final String DEFAULT_MY_SERVER = "liubinhao lbhbinhao@163.com";
    /**
     * 20M
     */
    private static final Long MINIMUM_SIZE = 1024*1024*20L;

    private static final Integer MINIMUM_TRANSFER_SIZE = 1024*1024*1;

    private static final Integer DEFAULT_LENGTH = 1024;

    @Override
    public HttpResponse handle(HttpReceivedMsg msg, Buffer buffer) {
        HttpResponse httpResponse = new HttpResponse();
        String range = msg.getHeaderMsg().get("Range");
        int[] rangeArea = range(range);
        httpResponse.setHttpTopRow(ResponseHeader.PARTIAL_RESPONSE_HEAD_LINE);
        StringBuilder responseHeaders = new StringBuilder();
        responseHeaders.append(ResponseHeader.CONTENT_TYPE).append(PropertiesConfig.readProperties(getType(msg)));
        responseHeaders.append("\r\n");
        if (rangeArea[1]!=0) {
            responseHeaders.append(ResponseHeader.CONTENT_LENGTH + (rangeArea[1] - rangeArea[0]+1));
        }else {
            responseHeaders.append(ResponseHeader.CONTENT_LENGTH + (rangeArea[1] - rangeArea[0]));
        }
        responseHeaders.append("\r\n");
        if (rangeArea[1]!=0) {
            responseHeaders.append(ResponseHeader.CONTENT_RANGE+rangeArea[0]+"-"+rangeArea[1]+"/"+buffer.capacity());
        }else {
            responseHeaders.append(ResponseHeader.CONTENT_RANGE+rangeArea[0]+"-"+DEFAULT_LENGTH+"/"+buffer.capacity());
        }
        responseHeaders.append("\r\n");
        responseHeaders.append(ResponseHeader.ACCEPT_RANGE);
        responseHeaders.append("\r\n");
        System.out.println(responseHeaders.toString());
        responseHeaders.append(ResponseHeader.KEEP_ALIVE);
        responseHeaders.append("\r\n");
        responseHeaders.append(ResponseHeader.SERVER_NAME).append(DEFAULT_MY_SERVER);
        httpResponse.setHttpHeader(responseHeaders.toString().getBytes());
        byte[] bytes = new byte[rangeArea[1] - rangeArea[0] + 1];
        System.out.println(buffer);
        if (rangeArea[1]!=0) {
            ((ByteBuffer)buffer).get(bytes,rangeArea[0],rangeArea[1] - rangeArea[0] + 1);
        }else {
            bytes = new byte[DEFAULT_LENGTH];
            ((ByteBuffer) buffer).get(bytes, rangeArea[0], DEFAULT_LENGTH);
        }
        httpResponse.setHttpBody(bytes);
        System.out.println(httpResponse);
        return httpResponse;
    }
    private int[] range(String range){
        int[] result = {0,0};
        String[] split1 = range.split("=");
        range = split1[split1.length-1];
        String[] split = range.split("-");
        if (split.length == 1) {
            result[0] = Integer.parseInt(split[0]);
        }
        if (split.length == 2) {
            result[0] = Integer.parseInt(split[0]);
            result[1] = Integer.parseInt(split[1]);
        }
        return result;
    }
}
