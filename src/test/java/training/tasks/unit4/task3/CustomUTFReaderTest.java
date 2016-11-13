package training.tasks.unit4.task3;

import org.junit.Test;

public class CustomUTFReaderTest {

    private final static String INPUT_FILES_PATH = "unit4/task3/";
    private final static String OUTPUT_FILES_PATH = "out/unit4/task3/";
    private final static String INPUT_FILE = "text.txt";
    private final static String OUTPUT_FILE = "converted_text.txt";

    @Test
    public void test() {
        CustomUTF8Reader reader = new CustomUTF8Reader(INPUT_FILES_PATH + INPUT_FILE);
        reader.read();

        CustomUTF16Writer writer = new CustomUTF16Writer(reader.getContent());
        writer.writeTo(OUTPUT_FILES_PATH + OUTPUT_FILE);
    }

}