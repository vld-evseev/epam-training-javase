package training.tasks.unit5.task1;

import java.io.*;

public class TextFileBrowser {

    private final static String EXTENSION = ".txt";

    public TextFileBrowser() {
    }

    public static String content(String path, String fileName) throws FileNotFoundException, IllegalArgumentException {
        checkExtension(fileName);

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + fileName))) {
            while (reader.ready()) {
                builder.append(reader.readLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Caused by " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to readValue a content of file " + path + fileName);
        }

        return builder.toString();
    }

    public static void create(String path, String fileName) throws FileNotFoundException, IllegalArgumentException {
        checkExtension(fileName);

        try (FileWriter fw = new FileWriter(path + fileName)) {
            fw.flush();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Caused by " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to create file " + path + fileName);
        }
    }

    public static boolean delete(String path, String fileName) throws FileNotFoundException, IllegalArgumentException {
        checkExtension(fileName);

        File file = new File(path + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + path + fileName + " does not exists");
        }

        return file.delete();
    }

    public static void write(String path, String fileName, String content) throws FileNotFoundException, IllegalArgumentException {
        checkExtension(fileName);

        try (FileWriter fw = new FileWriter(path + fileName, true)) {
            fw.append(content);
            fw.flush();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Caused by " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to write to file " + path + fileName);
        }
    }

    private static void checkExtension(String fileName) throws IllegalArgumentException {
        if (!fileName.endsWith(EXTENSION)) {
            throw new IllegalArgumentException(fileName + " is not a text file");
        }
    }
}
