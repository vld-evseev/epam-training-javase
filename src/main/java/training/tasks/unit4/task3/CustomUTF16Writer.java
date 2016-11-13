package training.tasks.unit4.task3;

import training.tasks.unit4.common.Writable;

import java.io.*;

public class CustomUTF16Writer implements Writable {

    private String content;

    public CustomUTF16Writer(String content) {
        this.content = content;
    }

    @Override
    public void writeTo(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName), "UTF-16"))) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
