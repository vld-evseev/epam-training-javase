package training.tasks.unit4.task2;

import training.tasks.unit4.common.Writable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CustomFileCharsWriter implements Writable {

    private Map<String, Integer> values;

    public CustomFileCharsWriter(Map<String, Integer> values) {
        this.values = values;
    }

    @Override
    public void writeTo(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName))) {

            for (Map.Entry<String, Integer> entry : values.entrySet()) {
                final String output = String.format("%s : %d\n", entry.getKey(), entry.getValue());
                bw.write(output);
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
