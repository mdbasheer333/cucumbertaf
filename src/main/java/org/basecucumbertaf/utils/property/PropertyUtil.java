package org.basecucumbertaf.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static String getProperty(String key) {
        Properties prop = loadPropertiesFile();
        return prop.getProperty(key);
    }

    public static Properties loadPropertiesFile() {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : config.properties");
        }
        return prop;
    }

}
