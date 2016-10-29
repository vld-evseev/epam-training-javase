package training.tasks.unit3.task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CrazyLogger {

    private static final int LOG_SIZE = 5000;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");
    private StringBuilder logBuilder;

    public CrazyLogger() {
        logBuilder = new StringBuilder();
        populateWithRandom();
    }

    public void log(String message) {
        buildMessage(message);
    }

    public List<String> search(String message) {
        List<String> founded = new ArrayList<>();

        try (Scanner scanner = new Scanner(logBuilder.toString())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(message.toLowerCase())) {
                    founded.add(line);
                }
            }
        }

        return founded;
    }

    public String getLog() {
        return logBuilder.toString();
    }

    private void populateWithRandom() {
        while (logBuilder.length() < LOG_SIZE) {
            buildMessage(String.valueOf(ThreadLocalRandom.current().nextInt()));
        }
    }

    private void buildMessage(String message) {
        logBuilder.append(FORMATTER.format(LocalDateTime.now())).append(" : ");
        logBuilder.append(Thread.currentThread());
        logBuilder.append(", ").append(message).append("\n");
    }
}
