package training.tasks.unit4.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceCodeParser {

    private final static String CLEAR_COMMENTS_PATTERN = "(/\\*+|\\s\\*|//|\".*\").*";
    private final static String SPLIT_PATTERN = "(\\s+|;\\s+|\\(+|\\)+)";
    private final static Pattern IGNORE_FORMATTING_PATTERN = Pattern.compile("[^\\s{2,}].*");

    private Map<String, Integer> keywords = new HashMap<>();
    private String sourceCode;

    public SourceCodeParser(String sourceCode) {
        this.sourceCode = clearWhitespaces(clearComments(sourceCode));
    }

    public void findKeyWords() {
        try (Scanner scanner = new Scanner(sourceCode)) {
            while (scanner.hasNext()) {
                final String nextLine = scanner.nextLine();

                for (String str : nextLine.split(SPLIT_PATTERN)) {
                    if (JavaKeyWords.KEYWORDS.contains(str)) {
                        if (keywords.containsKey(str)) {
                            keywords.put(str, keywords.get(str) + 1);
                        } else {
                            keywords.put(str, 1);
                        }
                    }
                }
            }
        }
    }

    public Map<String, Integer> getKeywords() {
        return keywords;
    }

    private String clearComments(String string) {
        return string.replaceAll(CLEAR_COMMENTS_PATTERN, "");
    }

    private String clearWhitespaces(String string) {
        final Matcher matcher = IGNORE_FORMATTING_PATTERN.matcher(string);
        final StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            builder.append(matcher.group()).append("\n");
        }

        return builder.toString();
    }


}
