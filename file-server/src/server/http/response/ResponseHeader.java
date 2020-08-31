package server.http.response;

/**
 * @author liubinhao
 * @date 2020/8/6
 */

public class ResponseHeader {
    public static final String CONTENT_ENCODING = "Content-Encoding: ";
    public static final String CONTENT_LENGTH   = "Content-Length: ";
    public static final String CONTENT_LANGUAGE = "Content-Language: ";
    public static final String CONTENT_TYPE     = "Content-Type: ";
    public static final String CONTENT_DISPOSITION = "Content-Disposition: ";
    public static final String TRANSFER_ENCODING   = "Transfer_Encoding ";
    public static final String SERVER_NAME         = "Server: ";
    public static final String CHUNKED_TRANSFER    = "Transfer-Encoding: chunked";
    public static final String KEEP_ALIVE    = "Connection: Keep-Alive";
    /** Content-Length: 119775
     *  Content-Range: bytes 11471135-11590909/27167764
     */
    public static final String CONTENT_RANGE = "Content-Range: bytes ";
    public static final String OCTET_STREAM  = "content-type: application/octet-stream";
    public static final byte[] DEFAULT_RESPONSE_HEAD_LINE = "HTTP/1.1 200 OK".getBytes();
    public static final byte[] PARTIAL_RESPONSE_HEAD_LINE = "HTTP/1.1 206 Partial Content".getBytes();
    public static final byte[] NEW_LINE_AND_CARRIAGE_RETURN = "\r\n".getBytes();

    public static final String ACCEPT_RANGE ="Accept-Ranges: bytes";


    public static final String ACCESS_CONTROL_ALLOW_HEADERS =
            "access-control-allow-headers: origin,origin,range,range";
    public static final String ACCESS_CONTROL_ALLOW_METHODS =
            "access-control-allow-methods: GET,OPTIONS,HEAD";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN  =
            "access-control-allow-origin: https://www.liubinhao.com";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS=
            "access-control-expose-headers: Content-Length,Content-Range,x-service-module";
}
