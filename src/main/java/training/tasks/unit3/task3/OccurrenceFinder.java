package training.tasks.unit3.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OccurrenceFinder {

    private String content;

    private static final Pattern significantSentencesPattern = Pattern.compile("[А-Я](\\s|[а-я]).*[.!?\\\\-]");
    private static final Pattern splitSentencesPattern = Pattern.compile("[.!?\\\\-](\\s|&nbsp;)");
    private static final Pattern firstPartOfSentencePattern = Pattern.compile("[А-Я].*\\([Рр]ис");
    private static final Pattern restPartOfSentencePattern = Pattern.compile("[0-9]+(\\-[а-я]|-[а-я],[а-я]|)\\)(\\([Рр]ис|.*)");
    private static final Pattern startOfStringPattern = Pattern.compile("^([А-Я]|&nbsp;[А-Я])");


    public OccurrenceFinder(String content) {
        this.content = content;
    }

    public List<String> find() {
        List<String> occurrences = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(content)) {
            while (scanner.hasNextLine()) {
                String token = scanner.nextLine();
                Matcher matcher = significantSentencesPattern.matcher(token);
                matcher.useTransparentBounds(true);


                while (matcher.find()) {
                    String subToken = matcher.group();
                    String[] sentences = subToken.split(splitSentencesPattern.pattern());

                    for (String sentence : sentences) {

                        if (matchStartOfString(sentence) && stringBuilder.length() > 0) {
                            occurrences.add(stringBuilder.toString());
                            stringBuilder.setLength(0);
                        }

                        if (matchFirstPartOfSentence(sentence)) {

                            stringBuilder.append(sentence).append(". ");
                        } else if (matchRestPartOfSentence(sentence)) {
                            stringBuilder.append(sentence).append(". ");
                        }
                    }
                }
            }
        }

        return occurrences;
    }

    private static boolean matchFirstPartOfSentence(String string) {
        Matcher matcher = firstPartOfSentencePattern.matcher(string);
        return matcher.find();
    }

    private static boolean matchRestPartOfSentence(String string) {
        Matcher matcher = restPartOfSentencePattern.matcher(string);
        return matcher.find();
    }

    private static boolean matchStartOfString(String string) {
        Matcher matcher = startOfStringPattern.matcher(string);
        return matcher.find();
    }

}
