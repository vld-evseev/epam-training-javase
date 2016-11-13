package training.tasks.unit4.task4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {

    public Object read(String filePath) {
        Object object = new Object();
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(filePath))) {
            object = inputStream.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return object;
        }
    }
}
