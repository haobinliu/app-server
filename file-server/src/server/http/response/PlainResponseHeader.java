package server.http.response;


import server.http.properties.PropertiesConfig;
/**
 * @author liubinhao
 * @date 2020/8/6
 */
public class PlainResponseHeader implements HeaderResponse{

    private static final String DEFAULT_MY_SERVER = "liubinhao lbhbinhao@163.com";

    @Override
    public byte[] generateHeader(String fileType,int fileLength) {
        StringBuilder responseHeaders = new StringBuilder();
        responseHeaders.append(ResponseHeader.CONTENT_TYPE).append(PropertiesConfig.readProperties(fileType));
        responseHeaders.append("\r\n");
        responseHeaders.append(ResponseHeader.CONTENT_LENGTH).append(fileLength);
        responseHeaders.append("\r\n");
        responseHeaders.append(ResponseHeader.SERVER_NAME).append(DEFAULT_MY_SERVER);
        return responseHeaders.toString().getBytes();
    }

}
