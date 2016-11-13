package training.tasks.unit4.task1;

import training.tasks.unit4.common.Writable;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class CustomFileOutputStreamWriter implements Writable {

    private Map<String, Integer> values;

    public CustomFileOutputStreamWriter(Map<String, Integer> values) {
        this.values = values;
    }

    @Override
    public void writeTo(String fileName) {
        try (BufferedOutputStream bos =
                     new BufferedOutputStream(
                             new FileOutputStream(fileName))) {

            for (Map.Entry<String, Integer> entry : values.entrySet()) {
                final String output = String.format("%s : %d\n", entry.getKey(), entry.getValue());
                bos.write(output.getBytes());
                bos.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
