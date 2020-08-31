package server.http.response;

/**
 * @author liubinhao
 * @date 2020/8/6
 */
public interface HeaderResponse {

    /**
     * 生成header
     * @param fileType the type of the file
     * @param fileLength the length of the file
     * @return response header
     */
    byte[] generateHeader(String fileType, int fileLength);

}
