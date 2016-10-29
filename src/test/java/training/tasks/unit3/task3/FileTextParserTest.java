package training.tasks.unit3.task3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class FileTextParserTest {

    @Test
    public void test() {
        Pattern pattern = Pattern.compile("[Рр]ис\\.\\s\\d{1,2}");
        FileTextParser textParser = new FileTextParser("unit3/task3/attachment.html");

        OccurrenceFinder finder = new OccurrenceFinder(textParser.getContent());
        List<String> matchesList = finder.find();
        List<Integer> numberList = new ArrayList<>();

        for (String string : matchesList) {
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                String number = matcher.group().replaceAll("[Рр]ис\\.\\s", "");
                numberList.add(Integer.valueOf(number));
            }
        }

        assertTrue(matchesList.size() > 0);
        assertTrue(numberList.size() > 0);

        List<Integer> sortedNumberList = new ArrayList<>(numberList);

        Collections.sort(sortedNumberList);

        if (sortedNumberList.equals(numberList)) {
            System.out.println("Images are going in sequence");
        } else {
            System.out.println("Images are not going in sequence");
        }

        for (String sentence : matchesList) {
            System.out.println(sentence);
        }
    }

}