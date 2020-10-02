package server.http.request;

/**
 * @author liubh
 * @date 2020/1/2 17:08
 */
public interface HttpMsgParser<T> {
    /**
     * 解读socket输入流
     * @param message http消息
     * @return 返回消息报文
     */
    T parse(String message);

}
