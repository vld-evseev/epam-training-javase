package training.tasks.unit4.task1;

import org.junit.Test;
import training.tasks.unit4.common.SourceCodeParser;

import java.util.TreeMap;

public class IOStreamsTest {

    private final static String OUTPUT_FILES_PATH = "out/unit4/task1/";
    private final static String INPUT_FILES_PATH = "unit4/task1/";
    private final static String[] INPUT_FILES = {"AbstractCollection.java",
            "BST.java",
            "HashMap.java",
            "Map.java",
            "Picture.java"};

    @Test
    public void test() {

        for (String file : INPUT_FILES) {
            CustomFileInputStreamReader reader = new CustomFileInputStreamReader(INPUT_FILES_PATH + file);
            reader.read();
            SourceCodeParser parser = new SourceCodeParser(reader.getContent());
            parser.findKeyWords();

            TreeMap<String, Integer> keywordsSortedByKey = new TreeMap<>(parser.getKeywords());
            CustomFileOutputStreamWriter writer = new CustomFileOutputStreamWriter(keywordsSortedByKey);

            writer.writeTo(OUTPUT_FILES_PATH + file + ".txt");
        }
    }
}