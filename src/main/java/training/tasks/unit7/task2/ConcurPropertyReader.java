package training.tasks.unit7.task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class ConcurPropertyReader {

    private String name;

    public ConcurPropertyReader(final String name) {
        this.name = name;
    }

    public String readValue(String key) {
        String value = "";
        final String path = path(name);

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(path))) {
            final Properties properties = new Properties();
            synchronized (name) {
                properties.load(reader);
                value = properties.getProperty(key);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
