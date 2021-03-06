package server.http.request;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liubh
 * @date 2020/1/2 16:58
 */
public class HttpReceivedMsg implements HttpMsgParser<HttpReceivedMsg> {

    private static final String CONTENT_SEPARATE_SIGN = "\r\n\r\n";

    private static final String LINE_SEPARATE_SIGN    = "\r\n";

    private static final String KV_SEPARATE_SIGN      = ":";

    private static final String SPACE_SEPARATE_SIGN   = "\\s+";

    private String requestMethod;

    private String requestUri;

    private String httpVersion;
    
    private String httpMsg;

    private Map<String,String> headerMsg;

    private byte[] requestBody;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getHttpMsg() {
        return httpMsg;
    }

    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }

    public Map<String, String> getHeaderMsg() {
        return headerMsg;
    }

    public void setHeaderMsg(Map<String, String> headerMsg) {
        this.headerMsg = headerMsg;
    }

    public byte[] getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public HttpReceivedMsg parse(String message) {
        HttpReceivedMsg httpReceivedMsg = new HttpReceivedMsg();
        String[] separateHttpMsg = message.split(CONTENT_SEPARATE_SIGN);
        if (separateHttpMsg.length==0){
            return null;
        }
        HashMap<String,String> hashMap = new HashMap<>(5);
        /*
         * length为1 没有请求体
         * length为2 有请求体
         */
        String[] lineSeparateInfo = separateHttpMsg[0].split(LINE_SEPARATE_SIGN);
        // http 请求首行
        httpReceivedMsg.setHttpMsg(lineSeparateInfo[0]);
        String[] firstRow = lineSeparateInfo[0].split(SPACE_SEPARATE_SIGN);
        //首部应有三个信息
        if (firstRow.length==3){
            httpReceivedMsg.setRequestMethod(firstRow[0]);
            httpReceivedMsg.setRequestUri   (firstRow[1]);
            httpReceivedMsg.setHttpVersion  (firstRow[2]);
        }
        for (int i = 1; i < lineSeparateInfo.length;i++){
            String[] keyValue = lineSeparateInfo[i].split(KV_SEPARATE_SIGN);
            hashMap.put(keyValue[0],keyValue[1]);
        }
        if (separateHttpMsg.length > 1){
            httpReceivedMsg.setRequestBody(lineSeparateInfo[1].getBytes());
        }
        httpReceivedMsg.setHeaderMsg(hashMap);
        return httpReceivedMsg;
    }


    @Override
    public String toString() {
        return "HttpReceivedMsg{" +
                "requestMethod='" + requestMethod + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                ", httpMsg='" + httpMsg + '\'' +
                ", headerMsg=" + headerMsg +
                ", requestBody=" + Arrays.toString(requestBody) +
                '}';
    }
}
