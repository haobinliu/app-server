package server.http.response;

import java.io.OutputStream;
/**
 * @author liubinhao
 * @date 2020/8/9
 */
public class HttpResponse{

    private OutputStream outputStream;

    private byte[] httpTopRow;

    private byte[] httpHeader;

    private byte[] httpBody;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public byte[] getHttpTopRow() {
        return httpTopRow;
    }

    public void setHttpTopRow(byte[] httpTopRow) {
        this.httpTopRow = httpTopRow;
    }

    public byte[] getHttpHeader() {
        return httpHeader;
    }

    public void setHttpHeader(byte[] httpHeader) {
        this.httpHeader = httpHeader;
    }

    public byte[] getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(byte[] httpBody) {
        this.httpBody = httpBody;
    }

    public void out() throws Exception {
        outputStream.write(this.httpTopRow);
        outputStream.write(ResponseHeader.NEW_LINE_AND_CARRIAGE_RETURN);
        outputStream.write(this.httpHeader);
        outputStream.write(ResponseHeader.NEW_LINE_AND_CARRIAGE_RETURN);
        outputStream.write(ResponseHeader.NEW_LINE_AND_CARRIAGE_RETURN);
        outputStream.write(this.httpBody);
        System.out.println("写出body");
    }
}
