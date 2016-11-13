package training.tasks.unit5.task2;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private String name;

    public PropertiesReader(String name) {
        this.name = name;
    }

    public String readValue(String key) throws KeyNotFoundException {
        String value = "";
        final Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(name));
            value = properties.getProperty(key);

            if (value == null) {
                throw new KeyNotFoundException("key not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
