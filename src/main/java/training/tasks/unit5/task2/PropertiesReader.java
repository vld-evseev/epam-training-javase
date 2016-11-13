package training.tasks.unit5.task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertiesReader {

    private String name;

    public PropertiesReader(String name) {
        this.name = name;
    }

    public String readValue(String key) throws KeyNotFoundException {
        String value = "";
        final String path = path(name);

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(path))) {
            final Properties properties = new Properties();
            properties.load(reader);

            value = properties.getProperty(key);
            if (value == null) {
                throw new KeyNotFoundException("key not found");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
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
