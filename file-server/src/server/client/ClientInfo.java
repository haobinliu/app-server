package server.client;

/**
 * @author liubinhao
 * @date 2020/8/9
 */

public class ClientInfo {

    private String clientName;

    private String clientPass;


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPass() {
        return clientPass;
    }

    public void setClientPass(String clientPass) {
        this.clientPass = clientPass;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientName='" + clientName + '\'' +
                ", clientPass='" + clientPass + '\'' +
                '}';
    }
}
