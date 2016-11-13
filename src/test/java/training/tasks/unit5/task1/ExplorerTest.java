package training.tasks.unit5.task1;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExplorerTest {

    private static final String RESOURCE_DIR = "src/main/resources/unit5/task1/";
    private static final String NEW_TEST_FILE = "new_test_file.txt";
    private static final String TEST_FILE_TO_DELETE = "test_file_to_delete.txt";

    @Test
    public void getDirectoryContentList() throws Exception {
        Explorer explorer = new Explorer();
        List<String> dirContent = explorer.getDirectoryContentList(RESOURCE_DIR);

        assertTrue(dirContent.size() > 0);

        for (String file : dirContent) {
            System.out.println(file);
        }
    }

    @Test
    public void createTextFile() throws Exception {
        Explorer explorer = new Explorer();
        explorer.deleteTextFile(RESOURCE_DIR, NEW_TEST_FILE);
        explorer.createTextFile(RESOURCE_DIR, NEW_TEST_FILE);
        assertTrue(new File(RESOURCE_DIR + NEW_TEST_FILE).exists());
    }

    @Test
    public void writeToTextFile() throws Exception {
        Explorer explorer = new Explorer();
        explorer.deleteTextFile(RESOURCE_DIR, NEW_TEST_FILE);
        explorer.createTextFile(RESOURCE_DIR, NEW_TEST_FILE);
        explorer.writeToTextFile(RESOURCE_DIR, NEW_TEST_FILE, "line1\n");
        explorer.writeToTextFile(RESOURCE_DIR, NEW_TEST_FILE, "line2\n");
        explorer.writeToTextFile(RESOURCE_DIR, NEW_TEST_FILE, "line3");

        String content = explorer.getTextFileContent(RESOURCE_DIR, NEW_TEST_FILE);
        assertTrue(content.contains("line1"));
        assertTrue(content.contains("line2"));
        assertTrue(content.contains("line3"));
    }

    @Test
    public void deleteTextFile() throws Exception {
        Explorer explorer = new Explorer();
        explorer.createTextFile(RESOURCE_DIR, TEST_FILE_TO_DELETE);
        File createdFile = new File(RESOURCE_DIR + TEST_FILE_TO_DELETE);
        assertTrue(createdFile.exists());

        explorer.deleteTextFile(RESOURCE_DIR, TEST_FILE_TO_DELETE);
        assertFalse(createdFile.exists());
    }

    @Test
    public void getTextFileContent() throws Exception {
        Explorer explorer = new Explorer();
        String content = explorer.getTextFileContent(RESOURCE_DIR, "testFile1.txt");

        assertTrue(content.contains("java"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileGetContent1() {
        Explorer explorer = new Explorer();
        explorer.getTextFileContent(RESOURCE_DIR, "test.dat");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileGetContent2() {
        Explorer explorer = new Explorer();
        explorer.getTextFileContent(RESOURCE_DIR + "123/", NEW_TEST_FILE);
    }


    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileCreate1() {
        Explorer explorer = new Explorer();
        explorer.createTextFile(RESOURCE_DIR, "test.dat");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileCreate2() {
        Explorer explorer = new Explorer();
        explorer.createTextFile(RESOURCE_DIR + "123/", NEW_TEST_FILE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileDelete1() {
        Explorer explorer = new Explorer();
        explorer.deleteTextFile(RESOURCE_DIR, "test.dat");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileDelete2() {
        Explorer explorer = new Explorer();
        explorer.deleteTextFile(RESOURCE_DIR + "123/", NEW_TEST_FILE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileWrite1() {
        Explorer explorer = new Explorer();
        explorer.writeToTextFile(RESOURCE_DIR, "test.dat", "some content");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsWhileWrite2() {
        Explorer explorer = new Explorer();
        explorer.writeToTextFile(RESOURCE_DIR + "123/", NEW_TEST_FILE, "some content");
    }

}