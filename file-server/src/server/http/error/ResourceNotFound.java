package server.http.error;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author liubinhao
 * @date 2020/8/9
 */
public class ResourceNotFound {

    private static final String PAGE_404    =
            "<!doctype html>"
            +"<html><head>"
            +"<meta charset=\"utf-8\">"
            +"<title>NO SUCH FILE FOUND</title>"
            +"</head><body>"
            +"</body><h3>If any problem occurs <br> feel free contact me,my email is:"
            +"lbhbinhao@163.com</h3></html>";

    private static final String NO_SUCH_FILE =
            "http/1.1 404 File Not Found"+
            "\r\n"+
            "Content-Type: text/html\r\n"+
            "Content-Length:"+PAGE_404.length()+"\r\n"+
            "Date: "+new Date()+"\r\n"+
            "Server: lbh\r\n\r\n"+
            PAGE_404;

    public static void noSuchFile(OutputStream out){
        try {
            out.write(NO_SUCH_FILE.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
