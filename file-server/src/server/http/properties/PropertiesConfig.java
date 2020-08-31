package server.http.properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @author liubinhao
 * @date 2020/8/6
 */
public class PropertiesConfig {

    private static final String mimePropertiesLocation = "D:\\company\\myself\\app-server\\file-server\\src\\server\\http\\contentype.properties";

    public static String readProperties(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(mimePropertiesLocation));
            return prop.get(key).toString();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
