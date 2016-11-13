package training.tasks.unit4.task2;

import org.junit.Test;
import training.tasks.unit4.common.SourceCodeParser;

import java.util.TreeMap;

public class ReaderWriterTest {

    private final static String OUTPUT_FILES_PATH = "out/unit4/task2/";
    private final static String INPUT_FILES_PATH = "unit4/task1/";
    private final static String[] INPUT_FILES = {"AbstractCollection.java",
            "BST.java",
            "HashMap.java",
            "Map.java",
            "Picture.java"};

    @Test
    public void test() {
        for (String file : INPUT_FILES) {
            CustomFileCharsReader reader = new CustomFileCharsReader(INPUT_FILES_PATH + file);
            reader.read();
            SourceCodeParser parser = new SourceCodeParser(reader.getContent());
            parser.findKeyWords();

            TreeMap<String, Integer> keywordsSortedByKey = new TreeMap<>(parser.getKeywords());
            CustomFileCharsWriter writer = new CustomFileCharsWriter(keywordsSortedByKey);

            writer.writeTo(OUTPUT_FILES_PATH + file + ".txt");
        }
    }

}