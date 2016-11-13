package training.tasks.unit5.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Explorer {

    public List<String> getDirectoryContentList(String dirPath) {
        return directoryContent(dirPath);
    }

    public void createTextFile(String path, String name) {
        try {
            TextFileBrowser.create(path, name);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void writeToTextFile(String path, String name, String content) {
        try {
            TextFileBrowser.write(path, name, content);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean deleteTextFile(String path, String name) {
        boolean deleted;
        try {
            deleted = TextFileBrowser.delete(path, name);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        return deleted;
    }

    private List<String> directoryContent(String dirPath) {
        File dir = new File(dirPath);
        List<String> dirList = new ArrayList<>();

        if (dir.exists() && dir.isDirectory()) {
            File[] listFiles = new File(dirPath).listFiles();

            if (listFiles != null) {
                for (File file : listFiles) {
                    dirList.add(file.getName());
                }
            }
        }

        return dirList;
    }


    public String getTextFileContent(String path, String name) {
        String content;
        try {
            content = TextFileBrowser.content(path, name);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        return content;
    }
}
