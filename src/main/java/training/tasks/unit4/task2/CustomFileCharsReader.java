package training.tasks.unit4.task2;

import training.tasks.unit4.common.Readable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomFileCharsReader implements Readable {

    private String filePath;
    private StringBuilder contentStringBuilder;

    public CustomFileCharsReader(String filePath) {
        super();
        this.filePath = filePath;
    }

    @Override
    public void read() {
        contentStringBuilder = new StringBuilder();
        final String path = path(filePath);

        if (!path.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {

                while (br.ready()) {
                    contentStringBuilder.append(br.read());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getContent() {
        return contentStringBuilder.toString();
    }

}
