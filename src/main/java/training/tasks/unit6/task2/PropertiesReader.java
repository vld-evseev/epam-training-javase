package training.tasks.unit6.task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*
    Если в map добавить элемент с ключем, который уже присутствует,
    то добавляемый элемент будет ассоциирован с этим ключем,
    а референс на предшествующий элемент будет удален, после чего
    он будет доступен для обработки Garbage Collector'ом
 */

public class PropertiesReader {

    private String name;

    public PropertiesReader(String name) {
        this.name = name;
    }

    public Map<String, String> read() {
        final String path = path(name);
        final Map<String, String> propertiesMap = new HashMap<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(path))) {
            final Properties properties = new Properties();
            properties.load(reader);

            final Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                final String key = entry.getKey().toString();
                final String value = entry.getValue().toString();
                propertiesMap.put(key, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertiesMap;
    }

    private String path(String filePath) {
        try {
            final URI io = ClassLoader.getSystemResource(filePath).toURI();
            return io.getPath();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(filePath + " is incorrect");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

}
