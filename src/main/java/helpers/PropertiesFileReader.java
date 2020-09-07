package helpers;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {


    @SneakyThrows
    public static String getSystemPropertyByName(String var) {

        FileInputStream fis;
        Properties property = new Properties();

        fis = new FileInputStream("config.properties");
        property.load(fis);

        return property.getProperty(var);

    }
}
