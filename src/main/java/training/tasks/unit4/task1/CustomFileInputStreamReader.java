package training.tasks.unit4.task1;

import training.tasks.unit4.common.Readable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomFileInputStreamReader implements Readable {

    private String filePath;
    private StringBuilder contentStringBuilder;

    public CustomFileInputStreamReader(String filePath) {
        super();
        this.filePath = filePath;
    }

    @Override
    public void read() {
        contentStringBuilder = new StringBuilder();
        final String path = path(filePath);

        if (!path.isEmpty()) {
            try (BufferedInputStream bis =
                         new BufferedInputStream(
                                 new FileInputStream(path))) {

                while (bis.available() > 0) {
                    contentStringBuilder.append((char) bis.read());
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
