package training.tasks.unit3.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTextReader {

    private String filePath;
    private String fileContent;

    public FileTextReader(String filePath) {
        this.filePath = filePath;
        fileContent = readFileContent();
    }

    public String getContent() {
        return fileContent;
    }

    private String readFileContent() {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), "UTF-8"))) {

            while (reader.ready()) {
                fileContent.append(reader.readLine()).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }

}
