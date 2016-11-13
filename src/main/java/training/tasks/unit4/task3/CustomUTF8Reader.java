package training.tasks.unit4.task3;

import training.tasks.unit4.common.Readable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomUTF8Reader implements Readable {

    private String filePath;
    private StringBuilder contentStringBuilder;

    public CustomUTF8Reader(String filePath) {
        super();
        this.filePath = filePath;
    }

    @Override
    public void read() {
        contentStringBuilder = new StringBuilder();

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), "UTF-8"))) {

            while (reader.ready()) {
                contentStringBuilder.append(reader.readLine()).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getContent() {
        return contentStringBuilder.toString();
    }
}
