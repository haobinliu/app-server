package server.common;

import server.FileOutputHandler;
import server.ServerRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author liubinhao
 * @date 2020/8/9
 */
public class ConfigInitializer {

    private static final String CONFIG_PATH = "D:\\company\\myself\\app-server\\file-server\\src\\server\\config\\context.properties";

    private static final String BASE_PATH_KEY  = "base.path";

    private static final String DEFAULT_BASE_PATH = "D:/image";

    private static final String SERVER_PORT    = "server.port";

    private static final Integer DEFAULT_LISTENING_PORT = 1215;

    public static void initializeConfig(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(CONFIG_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputHandler.FILE_PATH = prop.getProperty(BASE_PATH_KEY,DEFAULT_BASE_PATH);
        String ports = prop.getProperty(SERVER_PORT, DEFAULT_LISTENING_PORT.toString());
        ArrayList<Integer> list = new ArrayList<>();
        if (ports.contains(",")){
            String[] split = ports.split(",");
            try {
                for (String s : split) {
                    list.add(Integer.valueOf(s));
                }
            }catch (Exception e){
                list.add(DEFAULT_LISTENING_PORT);
                System.out.println("multi ports config error,please check the ports in context.properties!");
            }
        }else {
            list.add(Integer.valueOf(prop.getProperty(SERVER_PORT, DEFAULT_LISTENING_PORT.toString())));
        }
        ServerRunner.ports = list;
    }
}
