package com.lbh.request;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Copyright..@lbhbinhao@163.com
 * @author liubinhao
 * @date 2020/12/28
 * ++++ ______                           ______             ______
 * +++/     /|                         /     /|           /     /|
 * +/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |___________________   |     |____________|     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 */

public class ReqUtils {

    private static final String CONTENT_SEPARATE_SIGN = "\r\n\r\n";

    private static final String LINE_SEPARATE_SIGN = "\r\n";

    private static final String SPACE_SEPARATE_SIGN = "\\s+";

    private static final String KV_SEPARATE_SIGN = ":";

//**********************************解析请求*************************************
    private static void parseTopReq(Request req, String[] topReq){
        req.setMethod (topReq[0]);
        req.setUri    (topReq[1]);
        req.setVersion(topReq[2]);
    }


    private static void parseHeaders(Request req, String[] headerStr){
        HashMap<String, String> headers = new HashMap<>(12);
        for (String s : headerStr) {
            String[] keyValue = s.split(KV_SEPARATE_SIGN);
            headers.put(keyValue[0], keyValue[1]);
        }
        req.setHeaders(headers);
    }


    public static Request parse(String reqStr) {
        Request req = new Request();
        String[] httpInfo = reqStr.split(CONTENT_SEPARATE_SIGN);
        if (httpInfo.length==0){
            return null;
        }
        if (httpInfo.length > 1){
            req.setBody(httpInfo[1].getBytes());
        }
        String[] content  = httpInfo[0].split(LINE_SEPARATE_SIGN);
        // http first row of a http request
        String[] firstRow = content[0].split(SPACE_SEPARATE_SIGN);
        // http / get demo_path.type
        parseTopReq(req, firstRow);
        parseHeaders(req, Arrays.copyOfRange(content,1,content.length));
        return req;
    }
//***************************************************************************************


}
