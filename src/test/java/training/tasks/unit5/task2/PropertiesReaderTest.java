package training.tasks.unit5.task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertiesReaderTest {

    private static final String TEST_PROPS = "unit5/task2/test.properties";

    @Test
    public void read() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(TEST_PROPS);
        String value = propertiesReader.readValue("key1");
        assertEquals(value, "value1");
    }

    @Test(expected = KeyNotFoundException.class)
    public void keyNotFoundTest() throws KeyNotFoundException {
        PropertiesReader propertiesReader = new PropertiesReader(TEST_PROPS);
        propertiesReader.readValue("nonExistingKey");
    }
}